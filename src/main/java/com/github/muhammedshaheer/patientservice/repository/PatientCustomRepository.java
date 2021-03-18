package com.github.muhammedshaheer.patientservice.repository;

import com.github.muhammedshaheer.patientservice.model.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class PatientCustomRepository {

    private final MongoTemplate mongoTemplate;

    public PatientCustomRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Patient> findByPatientName(String keyword, Pageable pageable) {
        Criteria searchCriteria = new Criteria();
        if (!StringUtils.isEmpty(keyword)) {
            searchCriteria = Criteria.where("fullName").regex(".*" + keyword + ".*", "i");
        }

        Query query = new Query();
        query.addCriteria(searchCriteria);
        query.addCriteria(Criteria.where("deleted").is(false));
        query.with(pageable);
        return mongoTemplate.find(query, Patient.class);
    }
}
