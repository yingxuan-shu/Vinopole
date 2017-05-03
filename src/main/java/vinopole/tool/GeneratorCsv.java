package vinopole.tool;

import vinopole.dao.ResponseFormDAO;
import vinopole.model.ResponseForm;
import vinopole.model.Serie;
import vinopole.model.criteria.Criteria;
import vinopole.model.form.Form;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by kevmi on 18/11/2016.
 */
public class GeneratorCsv {
    /**
     * Generate csv of response from one serie
     * @param serie
     * @param file
     */
    public static void GenerateCsv(Serie serie, String file) {
        try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(new File(file + "\\" + serie.getId() + "_" + serie.getName() + ".csv")), "UTF-8")) {
            fileWriter.append("\uFEFF"); //ajout caractère pour encodage UTF-8 avec BOM
            Form f = serie.getForm(); //on récupère le formulaire de la série
            //entete du fichier
            fileWriter.append("N° juge;N° produit");
            List<Criteria> criterias = f.getCriterias();
            Collections.sort(criterias);
            for (Criteria c : criterias) {
                fileWriter.append(";").append(c.getName());
            }
            fileWriter.append("\n");
            ResponseFormDAO responseFormDAO = new ResponseFormDAO();
            List<ResponseForm> responses = responseFormDAO.getBySerie(serie.getId());
            long idJudge = responses.get(1).getJudge().getId();
            long idWine = responses.get(1).getWine().getId();
            fileWriter.append(String.valueOf(idJudge)).append(";").append(String.valueOf(idWine)).append(";");
            for (ResponseForm r : responses) {
                //si on change de vin ou de juge on change de ligne
                if (r.getJudge().getId() != idJudge || r.getWine().getId() != idWine) {
                    idJudge = r.getJudge().getId();
                    idWine = r.getWine().getId();
                    fileWriter.append("\n").append(String.valueOf(idJudge)).append(";").append(String.valueOf(idWine)).append(";");
                }
                fileWriter.append(String.valueOf(r.getResult())).append(";");
            }
            fileWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
