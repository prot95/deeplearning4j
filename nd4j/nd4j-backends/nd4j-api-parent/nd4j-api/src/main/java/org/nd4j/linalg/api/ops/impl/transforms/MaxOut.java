/*******************************************************************************
 * Copyright (c) 2015-2018 Skymind, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

package org.nd4j.linalg.api.ops.impl.transforms;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.imports.NoOpNameFoundException;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseTransformOp;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;

/**
 * Max out activation:
 * http://arxiv.org/pdf/1302.4389.pdf
 *
 * @author Adam Gibson
 */
public class MaxOut extends BaseTransformOp {

    private Number max = Double.NaN;

    public MaxOut(SameDiff sameDiff, SDVariable i_v, boolean inPlace, Number max) {
        super(sameDiff, i_v, inPlace);
        this.max = max;
    }

    public MaxOut(SameDiff sameDiff, SDVariable i_v, long[] shape, boolean inPlace, Object[] extraArgs, Number max) {
        super(sameDiff, i_v, shape, inPlace, extraArgs);
        this.max = max;
    }

    public MaxOut(SameDiff sameDiff, SDVariable i_v, Object[] extraArgs, Number max) {
        super(sameDiff, i_v, extraArgs);
        this.max = max;
    }

    public MaxOut() {}

    public MaxOut(INDArray x, INDArray z) {
        super(x, z);
    }

    public MaxOut(INDArray x, INDArray z, long n) {
        super(x, z, n);
    }

    public MaxOut(INDArray x, INDArray y, INDArray z, long n) {
        super(x, y, z, n);
    }

    public MaxOut(INDArray x) {
        super(x);
    }

    @Override
    public int opNum() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String opName() {
        return "maxout";
    }

    @Override
    public String onnxName() {
        throw new NoOpNameFoundException("No onnx op opName found for " +  opName());
    }


    @Override
    public String tensorflowName() {
        return "Maxout";
    }

    @Override
    public List<SDVariable> doDiff(List<SDVariable> f1) {
        return null;
    }

    @Override
    public DataType resultType() {
        return Nd4j.defaultFloatintPointType();
    }

    @Override
    public Type getOpType() {
        return Type.TRANSFORM_STRICT;
    }

    @Override
    public boolean validateDataTypes() {
        if (!x().isR())
            return false;

        if (y() != null && !y().isR())
            return false;

        if (z() != null && z().dataType() != x().dataType())
            return false;

        return true;
    }
}
