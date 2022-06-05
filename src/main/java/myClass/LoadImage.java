package myClass;

import com.example.pavnshop.HelloApplication;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс LoadImage методами <b>LoadImage</b>, <b>SaveImage</b>
 * <p>
 * Данный класс позволяет описать экземпляр выгрузить фотографию из подконревого каталога или сохранить фотографию туда.
 * В дальнейшем все методы могут быть изменены.
 * @author Автор Львова Ксения
 * @version 1.3
 */
public class LoadImage {
    /**
     * Метод находит фотографию товара в корневом каталоге по имени
     * @param nameImg имя фотографии
     * @return фотографию товара
     */
    public Image LoadImage(String nameImg){
        String path =  new File("").getAbsolutePath();
        javafx.scene.image.Image img = new Image(String.valueOf(Paths.get("./images/" + nameImg).toUri()));

        return img;
    }
    public Path path1;
    /**
     * Метод возвращает сохранена фотография в корневой каталог или нет
     * @param img фотография товара
     * @return
     * true - если сохранена
     * false - если нет
     */
    public Boolean SaveImage(File img){
        try {
            HelloApplication controller = new HelloApplication();
            //String path =  new File("").getAbsolutePath();
            //File newFile = new File(path + "\\image\\" + img.getName());
            Path path = Files.createDirectories(Path.of(String.valueOf(Paths.get("./images/" + img.getName()))));
            File newFile = new File(path.toString());
            BufferedImage input = ImageIO.read(img);
            ImageIO.write(input, "PNG", newFile);
            return true;
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка сохранения картинки "+e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }

}
