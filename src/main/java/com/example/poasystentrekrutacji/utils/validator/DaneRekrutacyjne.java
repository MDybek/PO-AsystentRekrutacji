package com.example.poasystentrekrutacji.utils.validator;

import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.DaneRekrutacyjneDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.UkonczoneStudiaDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.WynikZMaturyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaneRekrutacyjne implements ValidationStrategy<DaneRekrutacyjneDTO> {
    @Override
    public void validate(DaneRekrutacyjneDTO daneRekrutacyjneDTO) {
        checkIfNumberOfWynikiZMaturyCorrect(daneRekrutacyjneDTO.wynikiZMatury());
        checkIfWynikiZMaturyUnique(daneRekrutacyjneDTO.wynikiZMatury());
        checkIfUkonczoneStudiaUnique(daneRekrutacyjneDTO.ukonczoneStudia());
        checkIfHonorowaneOsiagnieciaIdsUnique(daneRekrutacyjneDTO.honorowaneOsiagnieciaIds());
    }

    private void checkIfNumberOfWynikiZMaturyCorrect(List<WynikZMaturyDTO> wynikZMaturyDTOS) {
        if(wynikZMaturyDTOS.size() < 3) {
            throw new RuntimeException(String.format("Należy wprowadzić co najmniej 3 wyniki z matury!"));
        }

    }

    private void checkIfWynikiZMaturyUnique(List<WynikZMaturyDTO> wynikZMaturyDTOs) {
        if(CollectionValidationUtils.isCollectionNonUnique(wynikZMaturyDTOs)) {
            throw new RuntimeException(String.format("Wyniki z matury muszą być unikalne!"));
        }
    }

    private void checkIfUkonczoneStudiaUnique(List<UkonczoneStudiaDTO> ukonczoneStudiaDTOs) {
        if(CollectionValidationUtils.isCollectionNonUnique(ukonczoneStudiaDTOs)) {
            throw new RuntimeException(String.format("Ukończone studia muszą być unikalne!"));
        }
    }

    private void checkIfHonorowaneOsiagnieciaIdsUnique(List<Long> ids) {
        if(CollectionValidationUtils.isCollectionNonUnique(ids)) {
            throw new RuntimeException(String.format("Id honorowanych osiągnięć muszą być unikalne!"));
        }
    }
}
