/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ctakes.assertion.train;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.ctakes.assertion.eval.AssertionEvaluation;
import org.apache.ctakes.assertion.medfacts.cleartk.AssertionCleartkAnalysisEngine;
import org.apache.ctakes.assertion.util.AssertionConst;


/**
 * For each assertion attribute (polarity, conditional, etc), run against the test directories
 * for that attribute, using models that are under the models-dir.
 * Note that this uses constants within {@link AssertionConst} for the directory names.
 */
public class PolarityDomainAdaptationTests {

	final static String RUN_ID = "feda_";
	
	protected final static String SHARP_TEST = AssertionConst.DATA_DIR + "preprocessed_data/sharp/test";
	protected final static String I2B2_TEST  = AssertionConst.DATA_DIR + "preprocessed_data/i2b2/test";
	protected final static String MIPACQ_TEST = AssertionConst.DATA_DIR + "preprocessed_data/mipacq/test";
	protected final static String NEGEX_TEST = AssertionConst.DATA_DIR + "preprocessed_data/negex";
	
	public static void main(String[] args) throws Exception {

		AssertionEvaluation.useEvaluationLogFile = true;
		AssertionEvaluation.evaluationLogFilePath = "eval/"+RUN_ID+new Date().toString().replaceAll(" ","_") + ".txt";

		ArrayList<TestPair> testGrid = new ArrayList<TestPair>();
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_FEDA, 	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_FEDA, 	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_FEDA, 	MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_FEDA, 	NEGEX_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.I2B2_FEDA,  	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.I2B2_FEDA,  	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.I2B2_FEDA,  	MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.I2B2_FEDA,  	NEGEX_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.MIPACQ_FEDA,  SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.MIPACQ_FEDA,  I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.MIPACQ_FEDA,  MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.MIPACQ_FEDA,  NEGEX_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.NEGEX_FEDA,  	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.NEGEX_FEDA,  	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.NEGEX_FEDA,  	MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.NEGEX_FEDA,  	NEGEX_TEST));  // not valid
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_FEDA,  	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_FEDA,  	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_FEDA,  	MIPACQ_TEST)); // not meaningful
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_FEDA,  	NEGEX_TEST));  // not meaningful
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_MIPACQ_FEDA,  SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_MIPACQ_FEDA,  I2B2_TEST));    // not meaningful
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_MIPACQ_FEDA,  MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_MIPACQ_FEDA,  NEGEX_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_NEGEX_FEDA,  	SHARP_TEST)); // not meaningful
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_NEGEX_FEDA,  	I2B2_TEST));  //not meaningful
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_NEGEX_FEDA,  	MIPACQ_TEST)); // not meaningful
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_NEGEX_FEDA,  	NEGEX_TEST));  // not valid
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.I2B2_MIPACQ_NEGEX_FEDA,  	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_MIPACQ_NEGEX_FEDA,  	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_NEGEX_FEDA,  		MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_FEDA,  	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_FEDA,  	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_FEDA,  	MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_FEDA,  	NEGEX_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_NEGEX_FEDA,  	SHARP_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_NEGEX_FEDA,  	I2B2_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_NEGEX_FEDA,  	MIPACQ_TEST));
		testGrid.add(new TestPair(PolarityDomainAdaptationTrain.SHARP_I2B2_MIPACQ_NEGEX_FEDA,  	NEGEX_TEST)); //not valid

		
		String attribute = "polarity";

		for (TestPair oneTest : testGrid) {
			ArrayList<String> params = new ArrayList<String>();

			File instancef = new File("eval/instances_"+
					oneTest.model.substring(oneTest.model.lastIndexOf("/")+1)+"_"+
					AssertionCleartkAnalysisEngine.normalizeToDomain(oneTest.data));
			
			params.add("--test-dir"); 	params.add(oneTest.data);
			params.add("--models-dir"); params.add(oneTest.model);
			String trainDomains = PolarityDomainAdaptationTrain.trainGrid.inverse().get(oneTest.model);
			if (null == trainDomains) { continue; }
			params.add("--train-dir"); 	params.add(trainDomains); // must list the train-dir in order to establish which domains
			//			params.add("--ytex-negation");
			//		params.add("--evaluation-output-dir");	params.add(AssertionConst.evalOutputDir);
			params.add("--test-only");	
			params.add("--feda");
			params.add("--print-instances");
			// hack-y way to name this
			params.add(instancef.getAbsolutePath());

			// Build up an "ignore" string
			for (String ignoreAttribute : AssertionConst.allAnnotationTypes) {
				if (!ignoreAttribute.equals(attribute)) { 

					if (ignoreAttribute.equals("historyOf")) {
						ignoreAttribute = ignoreAttribute.substring(0, ignoreAttribute.length()-2);
					}

					params.add("--ignore-" + ignoreAttribute);
				}
			}
			String[] paramList = params.toArray(new String[]{});

			System.out.println(Arrays.asList(paramList).toString());

			// Run the actual assertion test on just one attribute
			AssertionEvaluation.main( paramList );
		}
	}


	static class TestPair {
		String model;
		String data;
		TestPair (String a, String b) {
			model=a;
			data=b;
		}
	}
	

}
