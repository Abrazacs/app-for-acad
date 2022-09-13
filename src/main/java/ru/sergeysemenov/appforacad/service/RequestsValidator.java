package ru.sergeysemenov.appforacad.service;
import org.springframework.stereotype.Component;
import ru.sergeysemenov.appforacad.dtos.SystemItemImport;
import ru.sergeysemenov.appforacad.dtos.SystemItemImportRequest;


@Component
public class RequestsValidator {
    private final static String DATE_TIME_PATTERN = "^([+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24:?00)([.,]\\d+(?!:))?)?(\\17[0-5]\\d([.,]\\d+)?)?([zZ]|([+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$";

    public boolean validateImportRequest(SystemItemImportRequest request){
        if(request.getUpdateDate()== null || request.getItems() == null){ //проверка на null
            return false;
        } else if (!request.getUpdateDate().matches(DATE_TIME_PATTERN)){ // провекра формата даты
            return false;}
        else{
            for (SystemItemImport item:request.getItems()) {
                if(item.getId()==null) return false;  // проверка, что id присутствует
                switch (item.getType()){
                    case "FILE" -> {
                        if(item.getUrl().isEmpty() || item.getSize().isEmpty()) return false; //  в файле url и размер != null
                        else return item.getSize().get() >= 0 && item.getUrl().get().length() <= 255; // размер не менее 0 и url до 255 символов
                    }
                    case "FOLDER" ->{
                        return item.getUrl().isEmpty() && item.getSize().isEmpty(); // в папке url и размер должны быть null
                    }
                }
            }
        }
        return true;
    }

    public boolean validateId(String id){
        return id != null;
    }

}
