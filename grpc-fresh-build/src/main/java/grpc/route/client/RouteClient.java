package grpc.route.client;

import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import route.*;

import java.io.*;

/**
 * copyright 2018, gash
 *
 * Gash licenses this file to you under the Apache License, version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

public class RouteClient {
	private static long clientID = 501;
	private static int port = 2345;

	public static void main(String[] args) throws IOException {
		ManagedChannel ch = ManagedChannelBuilder.forAddress("localhost", RouteClient.port).usePlaintext().build();

		RequestServiceGrpc.RequestServiceBlockingStub stub = RequestServiceGrpc.newBlockingStub(ch);
//		RouteServiceGrpc.RouteServiceBlockingStub stub = RouteServiceGrpc.newBlockingStub(ch);

		int I = 1;
		int RTT_Time = 0;
		for (int i = 0; i < I; i++) {
			FileOutputStream os = new FileOutputStream("/Users/adarshpatil/Documents/Masters/Sem 2/275 Gash/Lab 1/Fresh Build/grpc-fresh-build/src/main/java/grpc/route/client/newFile.txt");
			Chunk_requested.Builder bld = Chunk_requested.newBuilder();
			bld.setResponseTime(RTT_Time);
//			Route.Builder bld = Route.newBuilder();
//			bld.setId(i);
//			bld.setOrigin(RouteClient.clientID);
//			bld.setPath("/to/somewhere");
//			byte[] hello = "hello".getBytes();
//			bld.setPayload(ByteString.copyFrom(hello));
//
//			Route r = stub.request(bld.build());

			bld.setOrigin(4);
			bld.setDestination(3);
			bld.setPath("tttt");
			bld.setChunkSize(8);
			bld.setOffset(4);

			// blocking!
			Chunk_response r = stub.request(bld.build());

			System.out.println( r.getPayload() + ", payload: " + r.getPayload().toStringUtf8());

			// TODO response handling
			String payload = new String(r.getPayload().toByteArray());
			System.out.println(", payload: " + payload);
			os.write(r.getPayload().toByteArray());
			os.flush();
			os.close();
			System.out.println( r.getPayload().toByteArray() + ", -- payload: ");
		}
		ch.shutdown();
	}
}
