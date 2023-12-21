package com.example.poasystentrekrutacji.dto.daneRekrutacyjne;

import java.util.List;

public record DaneRekrutacyjneDTO(List<WynikZMaturyDTO> wynikiZMatury,
                                  List<UkonczoneStudiaDTO> ukonczoneStudia,
                                  List<Long> honorowaneOsiagnieciaIds) {
}
