package com.example.demo.service;

/**
 * @Author yyhu3
 * @Date 2018-07-25 15:44
 */
import java.io.File;
import java.io.IOException;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IBundleCoverage;
import org.jacoco.core.tools.ExecFileLoader;
import org.jacoco.report.DirectorySourceFileLocator;
import org.jacoco.report.FileMultiReportOutput;
import org.jacoco.report.IReportVisitor;
import org.jacoco.report.html.HTMLFormatter;

public class testjacoco {

    private final String title;

    private final File executionDataFile;
    private final File classesDirectory;
    private final File sourceDirectory;
    private final File reportDirectory;

    private ExecFileLoader execFileLoader;
    public testjacoco(final File projectDirectory ) {
        this.title = projectDirectory.getName();
        this.executionDataFile = new File(projectDirectory, "jacoco-ipc-20180730.exec");//覆盖率的exec文件地址
        this.classesDirectory = new File(projectDirectory, "WEB-INF-1");//目录下必须包含源码编译过的class文件,用来统计覆盖率。所以这里用server打出的jar包地址即可
//        this.sourceDirectory =null;
//        this.sourceDirectory = new File("E:\\yyhu3\\WEB-INF-1\\classes\\", "src\\main\\java");//源码的/src/main/java,只有写了源码地址覆盖率报告才能打开到代码层。使用jar只有数据结果
        this.sourceDirectory = new File("E:\\yyhu3\\WEB-INF-1\\", "classes\\");
        this.reportDirectory = new File(projectDirectory, "coveragereport_ipc_073003");//要保存报告的地址
    }


    public void create() throws IOException {
        loadExecutionData();

        final IBundleCoverage bundleCoverage = analyzeStructure();

        createReport(bundleCoverage);

    }

    private void createReport(final IBundleCoverage bundleCoverage)
            throws IOException {
        final HTMLFormatter htmlFormatter = new HTMLFormatter();
        final IReportVisitor visitor = htmlFormatter
                .createVisitor(new FileMultiReportOutput(reportDirectory));

        visitor.visitInfo(execFileLoader.getSessionInfoStore().getInfos(),
                execFileLoader.getExecutionDataStore().getContents());
        visitor.visitBundle(bundleCoverage, new DirectorySourceFileLocator(
                sourceDirectory, "utf-8", 4));
        visitor.visitEnd();

    }

    private void loadExecutionData() throws IOException {
        execFileLoader = new ExecFileLoader();
        execFileLoader.load(executionDataFile);
    }

    private IBundleCoverage analyzeStructure() throws IOException {
        final CoverageBuilder coverageBuilder = new CoverageBuilder();
        final Analyzer analyzer = new Analyzer(
                execFileLoader.getExecutionDataStore(), coverageBuilder);

        analyzer.analyzeAll(classesDirectory);

        return coverageBuilder.getBundle(title);
    }

    public static void main(final String[] args) throws IOException {
        testjacoco generator = new testjacoco(new File("E:\\yyhu3\\"));//传递工程目录
        generator.create();
        System.out.println("执行结束");
    }
}