package com.laboratorios.labs.service;
import com.laboratorios.labs.event.LabsPlacedEvent;
import com.laboratorios.labs.model.Laboratorio;
import com.laboratorios.labs.model.MedicamentosLineList;
import com.laboratorios.labs.repositories.LaboratorioRepository;
import com.laboratorios.labs.service.notFoundExecption.ResourceNotFoundException;
import com.laboratorios.labs.service.notFoundExecption.ResourceNotFoundWebClient;
import com.marcasMedicamentos.brands.model.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private KafkaTemplate<String, LabsPlacedEvent> kafkaTemplate;

    private final WebClient webClient;

    public LaboratorioService(@LoadBalanced WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<Medicamento> medMonoGet(Long idM) {
        return webClient
                .get()
                .uri("http://brands-medicine/api/medicine/readId/" + idM)
                .retrieve()
                .bodyToMono(Medicamento.class)
                .onErrorResume(WebClientResponseException.NotFound.class, ex ->
                        Mono.error(new ResourceNotFoundWebClient(idM)));
    }

    // CREAT
    public Laboratorio creatTestObj(Laboratorio obj) {
        List<MedicamentosLineList> arrayListMedicamentos = new ArrayList<>();

        for (MedicamentosLineList lineList: obj.getListagemParaMedicamentos()) {

            Medicamento getIdFromJSONmedicine = medMonoGet(lineList.getIdMedicamentos()).block(); // Ids de medicamentos vindo do WebClient
            // getIdFromJSONmedicine = ID: 3

            if(getIdFromJSONmedicine != null) {
                System.out.println("Dados do medicamento: " + getIdFromJSONmedicine.getName());
                MedicamentosLineList classListOfMedicamentos = new MedicamentosLineList();
                classListOfMedicamentos.setIdMedicamentos(getIdFromJSONmedicine.getIdMedicine());
                classListOfMedicamentos.setName(getIdFromJSONmedicine.getPrincipio_ativo());
                classListOfMedicamentos.setPrice(getIdFromJSONmedicine.getPrice());
                classListOfMedicamentos.setListOfLaboratorio(obj);
                arrayListMedicamentos.add(classListOfMedicamentos);
            } else {
                return null;
            }
        }
        obj.setListagemParaMedicamentos(arrayListMedicamentos);
        Laboratorio saveObj = laboratorioRepository.save((obj));
        kafkaTemplate.send("notificationTopic", new LabsPlacedEvent(saveObj.getName()));
        return saveObj;
         /* public Mono<Laboratorio> testandoMonoAndFlux(Laboratorio obj) {
        // Retorna zero (nulo) ou um valor - Mono
        List<Mono<MedicamentosLineList>> monoList = obj.getListagemParaMedicamentos()
                .stream()
                // O que o map faz? Para cada elemento dentro da lista, ele a transforma e entrega uma nova.
                .map(medicamentosLineList -> medMonoGet(medicamentosLineList.getIdMedicamentos())// Recebemos esse ID do JSON.
                            .map(medicamento -> {
                                MedicamentosLineList list = new MedicamentosLineList();
                                list.setIdMedicamentos(medicamento.getIdMedicine());
                                list.setTestandoLaboratorio(obj);
                                return list;
                            }))
                .toList();
        // Tentando retornar uma lista de medicamentos a partir do Mono<MedicamentosLineList>

        // - Merge: Retorna todos os Monos em uma só lista.
        return Flux.merge(monoList)
                .collectList()
                .flatMap(listaDeMedicamentos -> {
                    obj.setListagemParaMedicamentos(listaDeMedicamentos);
                    return Mono.fromCallable(() -> laboratorioRepository.save(obj));
                });
    }*/
    }

    // READ All
    public List<Laboratorio> readAll(){
        return laboratorioRepository.findAll();
    }

    // READ BY ID
    public Laboratorio readById(Long idLab) {
        try {
            return laboratorioRepository.findById(idLab).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(idLab);
        }
    }

    // UPDATE OBJ
    public Laboratorio updateObj(Laboratorio oldObj, Long idLab) {
        try {
            Laboratorio newObj = laboratorioRepository.findById(idLab).get();
            DataUpdate(newObj, oldObj);
            return newObj;
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(idLab);
        }

    }
    private void DataUpdate(Laboratorio newObj, Laboratorio oldObj) {
        newObj.setName(oldObj.getName());
        newObj.setDescription(oldObj.getDescription());
        newObj.setTelephone(oldObj.getTelephone());
    }

    // Tentar fazer uma opção para que o usuário consiga deletar uma opção da lista.

    // DELETE OBJ
    public void deleteObj(Long idLab) {
        try{
            Laboratorio getIdObj = laboratorioRepository.findById(idLab).get();
            laboratorioRepository.delete(getIdObj);
        }catch (NoSuchElementException noSuchElementException) {
            throw new ResourceNotFoundException(idLab);
        }

    }
}
