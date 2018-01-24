package com.example.rest;

import com.example.model.LottoResultModel;
import com.example.repository.LottoResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by piotrek on 2018-01-24.
 * http://silversableprog.blogspot.com/2015/12/javaspring-boot-utworzenie.html
 */

@RestController                     // oznacza żę dana klasa jest kontollerem Spring, czyli według wzorca MVC  służy do powiązania danych z widokiem, w tym przypadku jest to REST
@RequestMapping("/rest/api/lotto") //URI tej aplikacji http://localhost:8070/rest/api/lotto
public class LottoResultController {

    @Autowired  //standardowo wykorzystuje wbudowane w Springa zarzadzanie DI i tworzy potrzebną implementacje interfejsu LottoResultRepository
    LottoResultRepository lottoResultRepository;

    @RequestMapping() // adnotacja oznacza że ta metoda jest domyślna metoda wykonywana dla klasowego mappingu czyli http://localhost:8070/rest/api/lotto
    public Iterable<LottoResultModel> getAllLottoResults(){
        return lottoResultRepository.findAll();
    }

    @RequestMapping("/{id}") // to samo co wyżej tylko przyjmuje parametr id który można wykorzystać
    public LottoResultModel getLottoResult(@PathVariable("id") Long id){    /*@PathVariable służy do pobierania zmiennych z URL.
                                                                            @RequestParam służy do pobierania zmiennych z wysłanego żądania.
                                                                            @RequestHeader daje nam dostęp do zmiennych w nagłówku wysyłanego żądania.*/
        return lottoResultRepository.findOne(id);
    }
}
