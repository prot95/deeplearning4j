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

package org.nd4j.linalg.api.ops.impl.shape.tensorops;

import onnx.OnnxProto3;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.buffer.DataType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TensorArrayRead extends BaseTensorOp {

    public TensorArrayRead(String name, SameDiff sameDiff, SDVariable[] args){
        super(name, sameDiff, args);
    }
    public TensorArrayRead(SameDiff sameDiff, SDVariable[] args){
        super(null, sameDiff, args);
    }

    public TensorArrayRead(){}
    @Override
    public String tensorflowName() {
        return "TensorArrayReadV3";
    }


    @Override
    public String opName() {
        return "tensorarrayread";
    }

    @Override
    public void initFromOnnx(OnnxProto3.NodeProto node, SameDiff initWith, Map<String, OnnxProto3.AttributeProto> attributesForNode, OnnxProto3.GraphProto graph) {
    }

    @Override
    public List<DataType> calculateOutputDataTypes(List<DataType> inputDataType){
        //Same output type as the TensorArray - which is defined by input 0
        SDVariable tArr = arg(0);
        TensorArray t3 = (TensorArray) sameDiff.getVariableOutputFunction(tArr.getVarName());
        DataType dt = t3.getTensorArrayDataType();
        return Collections.singletonList(dt);
    }
}
