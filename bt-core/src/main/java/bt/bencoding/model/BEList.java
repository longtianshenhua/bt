package bt.bencoding.model;

import bt.bencoding.BEEncoder;
import bt.bencoding.BEType;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BEList implements BEObject<List<? extends BEObject<?>>> {

    private byte[] content;
    private List<? extends BEObject<?>> value;
    private BEEncoder encoder;

    public BEList(byte[] content, List<? extends BEObject<?>> value) {
        this.content = content;
        this.value = Collections.unmodifiableList(value);
        encoder = BEEncoder.encoder();
    }

    @Override
    public BEType getType() {
        return BEType.LIST;
    }

    @Override
    public byte[] getContent() {
        return content;
    }

    @Override
    public List<? extends BEObject<?>> getValue() {
        return value;
    }

    @Override
    public void writeTo(OutputStream out) {
        encoder.encode(this, out);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof BEList)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        return value.equals(((BEList) obj).getValue());
    }
}
