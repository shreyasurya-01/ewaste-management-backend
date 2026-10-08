package EwasteManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import EwasteManagement.model.EWaste;

public interface EWasteRepository extends MongoRepository<EWaste, String> {

}
