// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chunk.proto

package route;

public final class Chunk {
  private Chunk() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_route_Chunk_response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_route_Chunk_response_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_route_Chunk_requested_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_route_Chunk_requested_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013chunk.proto\022\005route\"\206\001\n\016Chunk_response\022" +
      "\016\n\006offset\030\001 \001(\003\022\016\n\006origin\030\002 \001(\003\022\023\n\013desti" +
      "nation\030\003 \001(\003\022\017\n\007payload\030\004 \001(\014\022\014\n\004last\030\005 " +
      "\001(\010\022\014\n\004path\030\006 \001(\t\022\022\n\nchunk_size\030\007 \001(\003\"\217\001" +
      "\n\017Chunk_requested\022\023\n\006offset\030\001 \001(\003H\000\210\001\001\022\016" +
      "\n\006origin\030\002 \001(\003\022\023\n\013destination\030\003 \001(\003\022\014\n\004p" +
      "ath\030\004 \001(\t\022\025\n\rresponse_time\030\005 \001(\003\022\022\n\nchun" +
      "k_size\030\006 \001(\003B\t\n\007_offset2L\n\016RequestServic" +
      "e\022:\n\007request\022\026.route.Chunk_requested\032\025.r" +
      "oute.Chunk_response\"\000B\004H\001P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_route_Chunk_response_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_route_Chunk_response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_route_Chunk_response_descriptor,
        new java.lang.String[] { "Offset", "Origin", "Destination", "Payload", "Last", "Path", "ChunkSize", });
    internal_static_route_Chunk_requested_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_route_Chunk_requested_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_route_Chunk_requested_descriptor,
        new java.lang.String[] { "Offset", "Origin", "Destination", "Path", "ResponseTime", "ChunkSize", "Offset", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
