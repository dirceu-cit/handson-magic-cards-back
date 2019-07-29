package magiccards.services.impl;

import magiccards.entities.Card;
import magiccards.repositories.CardRepository;
import magiccards.services.ExpansionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExpansionServiceImpl implements ExpansionService {

     @Autowired
     CardRepository cardRepository;

     /**
      * It is to delete cards "in cascade" before expansion be deleted
      * @param expansionId
      */
     public void deleteCardsRelated(Integer expansionId){
          Collection<Card> cardsToDelete =cardRepository.findByExpansionId(expansionId);
       cardRepository.delete(cardsToDelete);
     }
}
