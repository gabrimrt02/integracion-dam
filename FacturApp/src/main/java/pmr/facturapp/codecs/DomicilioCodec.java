package pmr.facturapp.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import pmr.facturapp.classes.Domicilio;

public class DomicilioCodec implements Codec<Domicilio>{

    @Override
    public void encode(BsonWriter writer, Domicilio value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("provincia", value.getProvincia());
        writer.writeString("municipio", value.getMunicipio());
        writer.writeEndDocument();
    }

    @Override
    public Class<Domicilio> getEncoderClass() {
        return Domicilio.class;
    }

    @Override
    public Domicilio decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        String provincia = reader.readString("provincia");
        String municipio = reader.readString("municipio");
        reader.readEndDocument();
        return new Domicilio(provincia, municipio);
    }
    


}
