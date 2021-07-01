package org.victor.grpc.provider.conf

import io.grpc.ServerBuilder
import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer
import org.springframework.stereotype.Component

@Component
class GRpcServerConfiguration : GRpcServerBuilderConfigurer(){
    override fun configure(serverBuilder: ServerBuilder<*>?) {

    }
}