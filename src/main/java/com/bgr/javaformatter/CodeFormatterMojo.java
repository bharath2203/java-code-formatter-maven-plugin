package com.bgr.javaformatter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mojo(
        name = "code-format",
        defaultPhase = LifecyclePhase.INITIALIZE
)
public class CodeFormatterMojo extends AbstractMojo {

    final String path = "src/main/java";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Executing code format in path " +  path);

        File sourceDir = new File(path);
        List<File> allFilesInThePath = getAllJavaFilesInPath(sourceDir);
    }

    private List<File> getAllJavaFilesInPath(File file) {
        List<File> allJavaFiles = new ArrayList<>();
        if (!file.isDirectory()) {
           if(file.getName().endsWith(".java")) {
               allJavaFiles.add(file);
               return allJavaFiles;
           }
        }
        for(File childFile: Objects.requireNonNull(file.listFiles())) {
            allJavaFiles.addAll(getAllJavaFilesInPath(childFile));
        }
        return allJavaFiles;
    }
}
