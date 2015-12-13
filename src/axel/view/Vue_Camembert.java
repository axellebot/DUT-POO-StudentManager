package axel.view;

import axel.Observateur;
import axel.model.Etudiant;
import axel.model.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import sun.util.resources.cldr.ms.CalendarData_ms_BN;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by axell on 01/12/15.
 */
public class Vue_Camembert extends JInternalFrame implements Observateur {
    private Promotion promotion;
    private Camembert camembert;

    public Vue_Camembert(Promotion promotion) {
        setTitle("Départements d'origine");
        //setPreferredSize(new Dimension(100,100));
        setResizable(true);
        setVisible(true);

        this.promotion = promotion;

        //create a dataset...
        PieDataset dataset = createDataset();

        //create the chart...
        JFreeChart chart = createChart(dataset);

        //create a ChartPanel
        this.camembert = new Camembert(chart);

        // add the chart to a component...
        this.add(camembert);

        //ajout en tant que observateur
        this.promotion.addObservateur(this);
    }

    @Override
    public void update() {
        //create a dataset...
        PieDataset dataset = createDataset();

        //create the chart...
        JFreeChart chart = createChart(dataset);

        //create a ChartPanel
        this.camembert.setChart(chart);


        this.revalidate();
        this.repaint();
    }

    private class Camembert extends ChartPanel {
        public Camembert(JFreeChart chart) {
            super(chart);
        }
    }

    /**
     * Creates a sample dataset for the demo.
     *
     * @return A sample dataset.
     */
    private PieDataset createDataset() {


        HashMap<String, Integer> map = new HashMap();
        ArrayList<Etudiant> listEtu = promotion.getListeEtudiants();

        for (int i = 0; i < listEtu.size(); i++) {
            if (map.containsKey(listEtu.get(i).getDpt())) {
                map.put(listEtu.get(i).getDpt(), map.get(listEtu.get(i).getDpt()) + 1);
            } else {
                map.put(listEtu.get(i).getDpt(), 1);
            }

        }


        final DefaultPieDataset result = new DefaultPieDataset();


        for (String s : map.keySet()) {
            result.setValue(s, map.get(s));
        }


        //Example
        /*
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
        */


        return result;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     * @return A chart.
     */
    private JFreeChart createChart(final PieDataset dataset) {

        final JFreeChart chart = ChartFactory.createPieChart3D(
                "Répartition Géographique",  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;

    }


}
