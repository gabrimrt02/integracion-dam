package pmr.facturdroid.converters;

import org.bson.Document;

import pmr.facturdroid.classes.Domicilio;

public class DomicilioConverter {

    public static Document convert(Domicilio domicilio) {
        return new Document("provincia", domicilio.getProvincia())
                .append("municipio", domicilio.getMunicipio());
    }

    public static Domicilio convert(Document documento) {
        String provincia = documento.getString("provincia");
        String municipio = documento.getString("municipio");
        return new Domicilio(provincia, municipio);
    }

}
