package packVue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import packModele.Etudiant;
import packModele.Promotion;

public class VueCamembertChart extends AbstractVue {

    private Camembert camemb;

    public VueCamembertChart(Promotion file) {
        super(file);
        camemb = new Camembert();
        this.setContentPane(camemb);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        camemb.initCamembert();
    }

// internal class
    public class Camembert extends ChartPanel {

        public Camembert() {
            super(null);
            initCamembert();
        }

        public void initCamembert() {
            this.setPreferredSize(new Dimension(450, 350));
            final PieDataset dataset = createSampleDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);
            this.setChart(chart);
        }

        private PieDataset createSampleDataset() {
            final DefaultPieDataset result = new DefaultPieDataset();
            
            ArrayList<String> listDepartementUtilises = new ArrayList();
            
            for (Etudiant etu : promo.getListEtudiants()) {
                if (!dansListDepartement(etu.getDepartement(), listDepartementUtilises)) {
                    listDepartementUtilises.add(etu.getDepartement());
                }
            }
            
            Map<String, Integer> nbEtuParDep = new HashMap<>();
            
            for(String s : listDepartementUtilises){
                nbEtuParDep.put(s, 0);
                for(Etudiant e : promo.getListEtudiants()){
                    if(e.getDepartement().equals(s)){
                        nbEtuParDep.replace(s, nbEtuParDep.get(s), nbEtuParDep.get(s)+1);
                    }
                }
            }
            
            for(Map.Entry<String, Integer> nbEParD : nbEtuParDep.entrySet()){
                result.setValue(nbEParD.getKey(),nbEParD.getValue());
            }
            
            return result;
        }
        
        private boolean dansListDepartement(String s, ArrayList<String> listString) {
            if (listString.size() > 0) {
                for (String str : listString) {
                    if (s.equals(str)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        private JFreeChart createChart(final PieDataset dataset) {
            final JFreeChart chart = ChartFactory.createPieChart3D(
                    "DEPARTEMENTS", // chart title
                    dataset, // data
                    true, // include legend
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
}
