/*
 * Copyright 2017. Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.gaffer.parquetstore.serialisation;

import uk.gov.gchq.gaffer.exception.SerialisationException;

public class StringParquetSerialiser implements ParquetSerialiser<String> {

    private static final long serialVersionUID = 8716636182314160831L;

    @Override
    public String getParquetSchema(final String colName) {
        return "optional binary " + colName + " (UTF8);";
    }

    @Override
    public Object[] serialise(final String object) throws SerialisationException {
        return new Object[]{object};
    }

    @Override
    public String deserialise(final Object[] objects) throws SerialisationException {
        if (objects.length == 1 && objects[0] instanceof String) {
            return (String) objects[0];
        }
        return null;
    }

    @Override
    public String deserialiseEmpty() throws SerialisationException {
        return null;
    }

    @Override
    public boolean preservesObjectOrdering() {
        return true;
    }

    @Override
    public Object[] serialiseNull() {
        return new Object[0];
    }

    @Override
    public boolean canHandle(final Class clazz) {
        return String.class.equals(clazz);
    }
}
