package grpc.route.client;

import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import route.*;

import java.io.*;
import java.sql.Timestamp;

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

		FileOutputStream os = new FileOutputStream("/Users/adarshpatil/Documents/Masters/Sem 2/275 Gash/Lab 1/Fresh Build/grpc-fresh-build/src/main/java/grpc/route/client/newFile");

		int i = 0;
		long Threshold_RTT = 12;
		int Chunk_Size = 5;
		int Threshold_Chunk_Size = 5;

		long RTT_Time = 0;
		boolean isLast = false;
		while(!isLast){
			Chunk_requested.Builder bld = Chunk_requested.newBuilder();
			bld.setResponseTime(RTT_Time);

			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
			bld.setOrigin(4);

			bld.setDestination(3);
			bld.setPath("tttt");
			bld.setChunkSize(Chunk_Size);
			bld.setOffset(i*5);

			// blocking!
			Chunk_response r = stub.request(bld.build());

//			System.out.println(" isLast - "+r.getLast());
			isLast = r.getLast();
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			RTT_Time = timestamp2.getTime() - timestamp1.getTime();

			System.out.println(" - RTT_Time " + RTT_Time + " - Chunk_Size" + Chunk_Size );


			if(RTT_Time > Threshold_RTT){
				Chunk_Size = Chunk_Size * 2;
			} else if( Chunk_Size > Threshold_Chunk_Size ){
				Chunk_Size = Chunk_Size / 2;
			}

			// TODO response handling
			String payload = new String(r.getPayload().toByteArray());
			System.out.println(", payload: " + payload);
			os.write(r.getPayload().toByteArray());
			i = i + 1;
		}
		os.flush();
		os.close();
		ch.shutdown();
	}
}
