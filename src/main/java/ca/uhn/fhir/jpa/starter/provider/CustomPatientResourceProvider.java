package ca.uhn.fhir.jpa.starter.provider;

import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.instance.model.api.IIdType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomPatientResourceProvider implements IResourceProvider {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Class<Patient> getResourceType() {
        return Patient.class;
    }

    @Read
    public Patient read(@IdParam IIdType theId) {
        // Exemplo de chamada HTTP ao enclave
        // Substituir "http://enclave/patient/" pelo endpoint real do enclave.
        String url = "http://enclave/patient/" + theId.getIdPart();
        Patient patient = restTemplate.getForObject(url, Patient.class);
        return patient;
    }

    // Implementa outros métodos (create, update, search, etc.) conforme necessário
}