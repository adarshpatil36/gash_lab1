// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chunk.proto

package route;

public interface Chunk_responseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:route.Chunk_response)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 offset = 1;</code>
   * @return The offset.
   */
  long getOffset();

  /**
   * <code>int64 origin = 2;</code>
   * @return The origin.
   */
  long getOrigin();

  /**
   * <code>int64 destination = 3;</code>
   * @return The destination.
   */
  long getDestination();

  /**
   * <code>bytes payload = 4;</code>
   * @return The payload.
   */
  com.google.protobuf.ByteString getPayload();

  /**
   * <code>bool last = 5;</code>
   * @return The last.
   */
  boolean getLast();

  /**
   * <code>string path = 6;</code>
   * @return The path.
   */
  java.lang.String getPath();
  /**
   * <code>string path = 6;</code>
   * @return The bytes for path.
   */
  com.google.protobuf.ByteString
      getPathBytes();

  /**
   * <code>int64 chunk_size = 7;</code>
   * @return The chunkSize.
   */
  long getChunkSize();
}
