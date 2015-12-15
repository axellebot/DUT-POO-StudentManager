package axel.view;

import axel.Observateur;
import axel.model.Etudiant;
import axel.model.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by axell on 01/12/15.
 */
public class Vue_Histogramme extends JInternalFrame implements Observateur {
    private Promotion promotion;
    private Histogramme histogramme;
    static final String bacs[] = {"S", "ES", "STI", "STG", "Etranger", "Autre"};

    public Vue_Histogramme(Promotion promotion) {
        super("Histogramme",true,true,true,true);

        this.promotion = promotion;

        //create a dataset...
        CategoryDataset dataset = createDataset(promotion);

        //create the chart...
        JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        this.histogramme = new Histogramme(chart);

        //add ChartPanel/Camember to the actual InternalFrame (Vue_camembert)
        this.add(histogramme);

        //ajout en tant que observateur
        this.promotion.addObservateur(this);

        //Rendre visible
        setVisible(true);
    }

    @Override
    public void update() {
        //create a dataset...
        CategoryDataset dataset = createDataset(promotion);

        //create the chart...
        JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        this.histogramme.setChart(chart);

        this.revalidate();
        this.repaint();
    }

    private class Histogramme extends ChartPanel {
        public Histogramme(JFreeChart chart) {
            super(chart);
        }
    }

    private CategoryDataset createDataset(Promotion promotion) {

        Map<String, List<Etudiant>> map = new LinkedHashMap<>();

        for(Etudiant etudiant : promotion.getListeEtudiants()){
            if(map.containsKey(etudiant.getSerieBac())){
                List<Etudiant> etudiants = map.get(etudiant.getSerieBac());
                etudiants.add(etudiant);
            } else {
                List<Etudiant> list = new ArrayList<Etudiant>();
                list.add(etudiant);
                map.put(etudiant.getSerieBac(), list);
            }
        }

        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();

        for(int i = 0; i < bacs.length; i++){
            if(map.containsKey(bacs[i])){
                defaultCategoryDataset.addValue(map.get(bacs[i]).size(), bacs[i], "");
            } else {
                defaultCategoryDataset.addValue(0, bacs[i], "");
            }
        }

        for (String key : map.keySet()) {
            defaultCategoryDataset.addValue(map.get(key).size(), key, "");
        }

        return defaultCategoryDataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart3D(
                "Série BAC",      // chart title
                "Bacs",               // domain axis label
                "Nombre",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setNoDataMessage("No data to display");

        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
        );
        final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        return chart;
    }
}




