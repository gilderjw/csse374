package problem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AppLauncherBaseCaseTest.class,
	AppLauncherSupportTest.class,
	ApplicationLauncherTest.class,
	DirectoryEventTest.class,
	ExecutableFileRunnerTest.class,
	HtmlFileRunnerTest.class,
	TextFileRunnerTest.class,
	DirectoryMonitorServiceTest.class,
	DirectoryChangeLoggerTest.class
})
public class AllTests {

}
