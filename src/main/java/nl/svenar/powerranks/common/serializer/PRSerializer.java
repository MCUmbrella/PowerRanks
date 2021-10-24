/**
 * This file is part of PowerRanks, licensed under the MIT License.
 *
 * Copyright (c) svenar (Sven) <powerranks@svenar.nl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

package nl.svenar.powerranks.common.serializer;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serializes a given object into a Java map & deserializes a Java map into an
 * object with the given class.
 */
public class PRSerializer {

    /**
     * Serialize an object into a Map<String, Object>
     * 
     * @param object
     * @return Java map corresponding to the given object
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> serialize(Object object) {
        ObjectMapper m = new ObjectMapper();
        return m.convertValue(object, Map.class);
    }

    /**
     * Deserialize a Map<String, Object> into an object with the type of the given
     * class a extra argument.
     * 
     * @param <T>
     * @param map
     * @param clazz
     * @return Object corresponding to the given Java map and class
     */
    public <T> T deserialize(Map<String, Object> map, Class<T> clazz) {
        ObjectMapper m = new ObjectMapper();
        return clazz.cast(m.convertValue(map, clazz));
    }

}
