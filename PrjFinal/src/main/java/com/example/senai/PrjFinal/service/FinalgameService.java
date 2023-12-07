package com.example.senai.PrjFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.senai.PrjFinal.entities.Finalgame;
import com.example.senai.PrjFinal.repositories.FinalgameRepository;


@Service
public class FinalgameService {
	private final FinalgameRepository finalgameRepository;

	public FinalgameService (FinalgameRepository finalgameRepository) {
		this.finalgameRepository = finalgameRepository;
	}

	public Finalgame saveFinalgame(Finalgame finalgame) {
		return (Finalgame) finalgameRepository.save(finalgame);

	}

	public Finalgame getFinalgameById(Long id) {
		return (Finalgame) finalgameRepository.findById(id).orElse(null);
	}

	public List<Finalgame> getAllFinalgame() {
		return finalgameRepository.findAll();
	}

	public void deleteFinalgame(Long id) {
		finalgameRepository.deleteById(id);
	}
	
	public Finalgame updateFinalgame(Long id, Finalgame novoFinalgame) {
        Optional<Finalgame> FinalgameOptional = finalgameRepository.findById(id);
        if (FinalgameOptional.isPresent()) {
        	Finalgame finalgameExistente =FinalgameOptional.get();
        	finalgameExistente.setName(novoFinalgame.getName());
        	finalgameExistente.setGenero(novoFinalgame.getGenero());          
            return finalgameRepository.save(finalgameExistente); 
        } else {
            return null; 
        }
    }
	
}


