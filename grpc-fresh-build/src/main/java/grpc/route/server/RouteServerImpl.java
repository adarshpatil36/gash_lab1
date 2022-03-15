package grpc.route.server;

import java.io.*;
import java.util.Properties;


import com.google.protobuf.ByteString;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import route.Chunk_response;
import route.RequestServiceGrpc;

public class RouteServerImpl extends RequestServiceGrpc.RequestServiceImplBase {
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
	/*protected ByteString process(route.Route msg) {

		// TODO placeholder
		String content = new String(msg.getPayload().toByteArray());
		System.out.println("-- got: " + msg.getOrigin() + ", path: " + msg.getPath() + ", with: " + content);

		// TODO complete processing
		final String blank = "blank";
		byte[] raw = blank.getBytes();

		return ByteString.copyFrom(raw);
	}*/

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

//	@Override
	public void request(route.Chunk_requested request, StreamObserver<route.Chunk_response> responseObserver) {

		// TODO refactor to use RouteServer to isolate implementation from
		// transportation

		System.out.println("--- in request");
		route.Chunk_response.Builder builder = route.Chunk_response.newBuilder();
		// routing/header information
//		builder.setId(RouteServer.getInstance().getNextMessageID());
//		builder.setOrigin(RouteServer.getInstance().getServerID());
//		builder.setDestination(request.getOrigin());
//		builder.setPath(request.getPath());

		// do the work
//		builder.setPayload(process(request));


		try {
			File myObj = new File("/Users/adarshpatil/Documents/Masters/Sem 2/275 Gash/Lab 1/Fresh Build/grpc-fresh-build/src/main/java/grpc/route/server/file");
//			Scanner myReader = new Scanner(myObj);

			int sizeOfFiles = 90;
			byte[] buffer = new byte[sizeOfFiles];
			RandomAccessFile reader = new RandomAccessFile(myObj, "rw");
			try/*(FileInputStream fis = new FileInputStream(myObj);
			BufferedInputStream bis = new BufferedInputStream(fis))*/{
				//int bytesAmount = bis.read(buffer);
				reader.seek(0);
				int bytesAmount = reader.read(buffer);
				int offset =sizeOfFiles;
				/*while (bytesAmount > sizeOfFiles) {
					Chunk_response.Builder response = Chunk_response.newBuilder();
					byte[] smallerChunk = new byte[bytesAmount];
					System.arraycopy(buffer, 0, smallerChunk, 0, bytesAmount);

					response.setOrigin(3);
					response.setDestination(8);
					response.setLast(false);
					response.setPath("wwww");

					response.setOffset(offset);
					response.setOrigin(request.getOrigin());
					response.setPayload(ByteString.copyFrom(smallerChunk));

					route.Chunk_response rtn = builder.build();

					responseObserver.onNext(rtn);
					responseObserver.onCompleted();
					bytesAmount-=sizeOfFiles;
					offset += sizeOfFiles;
				}*/
				//if(bytesAmount < sizeOfFiles){
					Chunk_response.Builder response = Chunk_response.newBuilder();

					byte[] smallerChunk = new byte[bytesAmount];
					System.arraycopy(buffer, 0, smallerChunk, 0, bytesAmount);
					offset = bytesAmount;

					response.setOrigin(3);
					response.setDestination(8);
					response.setLast(false);
					response.setPath("wwww");

					response.setOffset(offset);
					response.setOrigin(request.getOrigin());

					System.out.println(" --- "+ bytesAmount+"  --- "+smallerChunk);
					response.setPayload(ByteString.copyFrom(smallerChunk));
					System.out.println(response.getPayload().toStringUtf8());
					route.Chunk_response rtn = response.build();

					responseObserver.onNext(rtn);
					responseObserver.onCompleted();
				//}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}
}
