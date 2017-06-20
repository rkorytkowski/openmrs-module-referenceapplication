package org.openmrs.module.referenceapplication.reporting.reports;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.openmrs.Cohort;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.dataset.DataSetRow;
import org.openmrs.module.reporting.dataset.SimpleDataSet;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.report.ReportData;
import org.openmrs.module.reporting.report.manager.ReportManager;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;

/**
 * Tests the NumberOfPatientRegistrations report
 */
public class NumberOfPatientRegistrationsTest extends ReportManagerTest {

	@Autowired
	NumberOfPatientRegistrations numberOfPatientRegistrations;

	@Override
	public ReportManager getReportManager() {
		return numberOfPatientRegistrations;
	}

	@Override
	public EvaluationContext getEvaluationContext() {
		EvaluationContext context = new EvaluationContext();
		context.addParameterValue("startDate", DateUtil.getDateTime(2017,6,1));
		context.addParameterValue("endDate", DateUtil.getDateTime(2017,6,16));
		context.setBaseCohort(new Cohort("50, 51"));
		return context;
	}

	@Override
	public void verifyData(ReportData data) {
		SimpleDataSet dataSet = (SimpleDataSet) data.getDataSets().values().iterator().next();

		Assert.assertThat(dataSet.getRows(), contains(hasData("OpenMRS ID", "10004M")));
		Assert.assertThat(dataSet.getRows(), contains(hasData("Given Name", "Jude")));
	}



}
