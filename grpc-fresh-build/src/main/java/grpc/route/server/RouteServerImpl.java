package grpc.route.server;

import java.io.*;
import java.util.Properties;

import java.util.Scanner; // Import the Scanner class to read text files


import com.google.protobuf.ByteString;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import route.RouteServiceGrpc.RouteServiceImplBase;

public class RouteServerImpl extends RouteServiceImplBase {
	private Server svr;

	private static Properties getConfiguration(final File path) throws IOException {
		if (!path.exists())
			throw new IOException("missing file");

		Properties rtn = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			rtn.load(fis);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return rtn;
	}

	/**
	 * TODO refactor this!
	 *
	 * @param path
	 * @param payload
	 * @return
	 */
	protected ByteString process(route.Route msg) {

		// TODO placeholder
		String content = new String(msg.getPayload().toByteArray());
		System.out.println("-- got: " + msg.getOrigin() + ", path: " + msg.getPath() + ", with: " + content);

		// TODO complete processing
		final String blank = "blank";
		byte[] raw = blank.getBytes();

		return ByteString.copyFrom(raw);
	}

	public static void main(String[] args) throws Exception {
		// TODO check args!

		String path = args[0];
		try {
			Properties conf = RouteServerImpl.getConfiguration(new File(path));
			RouteServer.configure(conf);

			final RouteServerImpl impl = new RouteServerImpl();
			impl.start();
			impl.blockUntilShutdown();

		} catch (IOException e) {
			// TODO better error message
			e.printStackTrace();
		}
	}

	private void start() throws Exception {
		svr = ServerBuilder.forPort(RouteServer.getInstance().getServerPort()).addService(new RouteServerImpl())
				.build();

		System.out.println("-- starting server");
		svr.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				RouteServerImpl.this.stop();
			}
		});
	}

	protected void stop() {
		svr.shutdown();
	}

	private void blockUntilShutdown() throws Exception {
		svr.awaitTermination();
	}

	@Override
	public void request(route.Route request, StreamObserver<route.Route> responseObserver) {

		// TODO refactor to use RouteServer to isolate implementation from
		// transportation

		route.Route.Builder builder = route.Route.newBuilder();

		// routing/header information
		builder.setId(RouteServer.getInstance().getNextMessageID());
		builder.setOrigin(RouteServer.getInstance().getServerID());
		builder.setDestination(request.getOrigin());
		builder.setPath(request.getPath());

		// do the work
//		builder.setPayload(process(request));

		try {
			File myObj = new File("/Users/adarshpatil/Documents/Masters/Sem 2/275 Gash/Lab 1/Fresh Build/grpc-fresh-build/src/main/java/grpc/route/server/file");
			Scanner myReader = new Scanner(myObj);

			int sizeOfFiles = 45;
			byte[] buffer = new byte[sizeOfFiles];

			try(FileInputStream fis = new FileInputStream(myObj);
			BufferedInputStream bis = new BufferedInputStream(fis)){
				int bytesAmount = bis.read(buffer);
				while (bytesAmount > sizeOfFiles) {
					byte[] smallerChunk = new byte[bytesAmount];
					System.arraycopy(buffer, 0, smallerChunk, 0, bytesAmount);
					builder.setPayload(ByteString.copyFrom(smallerChunk));
					bytesAmount-=sizeOfFiles;
//						System.out.println(buffer+" -- "+bytesAmount);
//						builder.setPayload(ByteString.copyFrom(buffer));
				}
				if(bytesAmount < sizeOfFiles){
					byte[] smallerChunk = new byte[bytesAmount];
					System.arraycopy(buffer, 0, smallerChunk, 0, bytesAmount);
					builder.setPayload(ByteString.copyFrom(smallerChunk));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		route.Route rtn = builder.build();
		responseObserver.onNext(rtn);
		responseObserver.onCompleted();
	}
}
