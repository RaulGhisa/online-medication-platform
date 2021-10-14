package rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: rpc_medication.proto")
public final class MedicationGrpc {

  private MedicationGrpc() {}

  public static final String SERVICE_NAME = "Medication";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<rpc.RpcMedication.Patient,
      rpc.RpcMedication.Med> getGetMedicationPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMedicationPlan",
      requestType = rpc.RpcMedication.Patient.class,
      responseType = rpc.RpcMedication.Med.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<rpc.RpcMedication.Patient,
      rpc.RpcMedication.Med> getGetMedicationPlanMethod() {
    io.grpc.MethodDescriptor<rpc.RpcMedication.Patient, rpc.RpcMedication.Med> getGetMedicationPlanMethod;
    if ((getGetMedicationPlanMethod = MedicationGrpc.getGetMedicationPlanMethod) == null) {
      synchronized (MedicationGrpc.class) {
        if ((getGetMedicationPlanMethod = MedicationGrpc.getGetMedicationPlanMethod) == null) {
          MedicationGrpc.getGetMedicationPlanMethod = getGetMedicationPlanMethod = 
              io.grpc.MethodDescriptor.<rpc.RpcMedication.Patient, rpc.RpcMedication.Med>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Medication", "GetMedicationPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpc.RpcMedication.Patient.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpc.RpcMedication.Med.getDefaultInstance()))
                  .setSchemaDescriptor(new MedicationMethodDescriptorSupplier("GetMedicationPlan"))
                  .build();
          }
        }
     }
     return getGetMedicationPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rpc.RpcMedication.Taken,
      rpc.RpcMedication.Empty> getSendMedicationTakenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMedicationTaken",
      requestType = rpc.RpcMedication.Taken.class,
      responseType = rpc.RpcMedication.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rpc.RpcMedication.Taken,
      rpc.RpcMedication.Empty> getSendMedicationTakenMethod() {
    io.grpc.MethodDescriptor<rpc.RpcMedication.Taken, rpc.RpcMedication.Empty> getSendMedicationTakenMethod;
    if ((getSendMedicationTakenMethod = MedicationGrpc.getSendMedicationTakenMethod) == null) {
      synchronized (MedicationGrpc.class) {
        if ((getSendMedicationTakenMethod = MedicationGrpc.getSendMedicationTakenMethod) == null) {
          MedicationGrpc.getSendMedicationTakenMethod = getSendMedicationTakenMethod = 
              io.grpc.MethodDescriptor.<rpc.RpcMedication.Taken, rpc.RpcMedication.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Medication", "SendMedicationTaken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpc.RpcMedication.Taken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpc.RpcMedication.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new MedicationMethodDescriptorSupplier("SendMedicationTaken"))
                  .build();
          }
        }
     }
     return getSendMedicationTakenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MedicationStub newStub(io.grpc.Channel channel) {
    return new MedicationStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MedicationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MedicationBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MedicationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MedicationFutureStub(channel);
  }

  /**
   */
  public static abstract class MedicationImplBase implements io.grpc.BindableService {

    /**
     */
    public void getMedicationPlan(rpc.RpcMedication.Patient request,
        io.grpc.stub.StreamObserver<rpc.RpcMedication.Med> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMedicationPlanMethod(), responseObserver);
    }

    /**
     */
    public void sendMedicationTaken(rpc.RpcMedication.Taken request,
        io.grpc.stub.StreamObserver<rpc.RpcMedication.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMedicationTakenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMedicationPlanMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                rpc.RpcMedication.Patient,
                rpc.RpcMedication.Med>(
                  this, METHODID_GET_MEDICATION_PLAN)))
          .addMethod(
            getSendMedicationTakenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpc.RpcMedication.Taken,
                rpc.RpcMedication.Empty>(
                  this, METHODID_SEND_MEDICATION_TAKEN)))
          .build();
    }
  }

  /**
   */
  public static final class MedicationStub extends io.grpc.stub.AbstractStub<MedicationStub> {
    private MedicationStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationStub(channel, callOptions);
    }

    /**
     */
    public void getMedicationPlan(rpc.RpcMedication.Patient request,
        io.grpc.stub.StreamObserver<rpc.RpcMedication.Med> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetMedicationPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMedicationTaken(rpc.RpcMedication.Taken request,
        io.grpc.stub.StreamObserver<rpc.RpcMedication.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMedicationTakenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MedicationBlockingStub extends io.grpc.stub.AbstractStub<MedicationBlockingStub> {
    private MedicationBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<rpc.RpcMedication.Med> getMedicationPlan(
        rpc.RpcMedication.Patient request) {
      return blockingServerStreamingCall(
          getChannel(), getGetMedicationPlanMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpc.RpcMedication.Empty sendMedicationTaken(rpc.RpcMedication.Taken request) {
      return blockingUnaryCall(
          getChannel(), getSendMedicationTakenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MedicationFutureStub extends io.grpc.stub.AbstractStub<MedicationFutureStub> {
    private MedicationFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MedicationFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MedicationFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MedicationFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpc.RpcMedication.Empty> sendMedicationTaken(
        rpc.RpcMedication.Taken request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMedicationTakenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MEDICATION_PLAN = 0;
  private static final int METHODID_SEND_MEDICATION_TAKEN = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MedicationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MedicationImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_MEDICATION_PLAN:
          serviceImpl.getMedicationPlan((rpc.RpcMedication.Patient) request,
              (io.grpc.stub.StreamObserver<rpc.RpcMedication.Med>) responseObserver);
          break;
        case METHODID_SEND_MEDICATION_TAKEN:
          serviceImpl.sendMedicationTaken((rpc.RpcMedication.Taken) request,
              (io.grpc.stub.StreamObserver<rpc.RpcMedication.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MedicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MedicationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return rpc.RpcMedication.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Medication");
    }
  }

  private static final class MedicationFileDescriptorSupplier
      extends MedicationBaseDescriptorSupplier {
    MedicationFileDescriptorSupplier() {}
  }

  private static final class MedicationMethodDescriptorSupplier
      extends MedicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MedicationMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MedicationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MedicationFileDescriptorSupplier())
              .addMethod(getGetMedicationPlanMethod())
              .addMethod(getSendMedicationTakenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
