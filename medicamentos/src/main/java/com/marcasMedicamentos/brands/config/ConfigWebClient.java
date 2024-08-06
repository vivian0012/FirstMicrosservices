package com.marcasMedicamentos.brands.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


// Vai configurar o WebClient. @Configuration + @Bean ajuda o Spring a gerenciar
// bibliotecas externas que o próprio não tem o costume de gerenciar.
// Tenha ciência disso, pois, em caso de ter uma classe que dependa de uma funcionalidade de fora do Spring,
// Será necessário a configuração para ele fazer essa injeção de dependências.
@Configuration
public class ConfigWebClient {
    /*
    Quando falamos de múltiplas instâncias de um serviço,
    estamos nos referindo a várias cópias do mesmo serviço executando simultaneamente.
    Muito comum em Microsserviços e principalmente quando usar o serviço de descoberta (Eureka).
    Por isso o uso do @LoadBalanced no WebClient. Ele é quem vai cuidar do balanceamento de cargas...
    */

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
