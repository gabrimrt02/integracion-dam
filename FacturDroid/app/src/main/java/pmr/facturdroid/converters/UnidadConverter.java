package pmr.facturdroid.converters;

import org.bson.Document;

import pmr.facturdroid.classes.statics.Unidad;

public class UnidadConverter {

    public static Document convert(Unidad unidad) {
        return new Document("unidad", unidad.getUnidad());
    }

    public static Unidad convert(Document documento) {
        Unidad returnable = null;
        switch(documento.getString("unidad")) {
            case "KILO(S)":
                returnable = Unidad.KILOS();
                break;
            case "CAJA(S)":
                returnable = Unidad.CAJAS();
                break;
            case "UNIDAD(ES) SUELTA(S)":
                returnable = Unidad.UNIDADES_SUELTAS();
                break;
        }

        return returnable;
    }

}
