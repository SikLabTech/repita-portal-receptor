package tech.siklab.portalrpa.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class IndexController {

    private static final String uploadDirectory = System.getProperty("user.dir") + "/Documents/Projetos RPA";

    @PostMapping(value = "/exe")
    public String exe(MultipartFile file) {

        Path fileNameAndPath = null;
        if (!file.isEmpty()) {
            fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            try {
                if (!Files.exists(Paths.get(uploadDirectory))) {
                    Files.createDirectories(Paths.get(uploadDirectory));
                }
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("-------------------------------------- Executando " + fileNameAndPath.toString() + "--------------------------------------\n\n");

        String saida = "";

        try {
            File bitbucket = new File("C:/ProcessLog.txt");
            if (Files.exists(bitbucket.toPath())) {
                Files.delete(bitbucket.toPath());
            }
            Process p = new ProcessBuilder("java", "-Xmx1024m", "-jar", fileNameAndPath.toString())
                    .redirectOutput(ProcessBuilder.Redirect.appendTo(bitbucket))
                    .redirectError(ProcessBuilder.Redirect.appendTo(bitbucket))
                    .start();
            
            try {
                p.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }
            InputStream is = new FileInputStream(bitbucket);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                System.out.println(line);
                line = buf.readLine();
            }

            saida = sb.toString();

            is.close();
            buf.close();

            p.destroy();

            if (saida.isEmpty()) {
                saida = "Robô executado com sucesso!";
            }
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("-------------------------------------- // --------------------------------------\n\n");

        return saida;
    }
}
