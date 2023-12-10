package cl.ucn.disc.as.conserjeria.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * The gRPC service class
 * @author Guillermo Fuentes
 */
public final class PersonaGrpcServiceImpl extends PersonaGrpcServiceGrpc.PersonaGrpcServiceImplBase{
    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void retrieve(PersonaGrpcRequest request, StreamObserver<PersonaGrpcResponse> responseObserver) {

        PersonaGrpc personaGrpc = PersonaGrpc.newBuilder()
                .setRut("204163499")
                .setNombre("Guillermo")
                .setApellidos("Fuentes Avila")
                .setEmail("guillermo.fuentes01@alumnos.ucn.cl")
                .setTelefono("+569123456")
                .build();

        responseObserver.onNext(PersonaGrpcResponse.newBuilder().setPersona(personaGrpc).build());

        responseObserver.onCompleted();
    }

}
