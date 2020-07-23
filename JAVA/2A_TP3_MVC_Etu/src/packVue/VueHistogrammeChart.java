package packVue;

import java.awt.Dimension;
import java.util.Observable;
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
import packModele.Promotion;

public class VueHistogrammeChart extends AbstractVue {

    private final Histogramme histo;

    public VueHistogrammeChart(Promotion promo) {
        super(promo);
        histo = new Histogramme();
        this.setContentPane(histo);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        histo.initHistogramme();
    }
    
    


    // internal class
    public class Histogramme extends ChartPanel {

        private CategoryDataset dataset;
        private JFreeChart chart;
        private ChartPanel chartPanel;
        
        public Histogramme() {
            super(null);
            this.setPreferredSize(new Dimension(297, 350));
            initHistogramme();
        }
        
        public void initHistogramme(){
            dataset = createDataset();
            chart = createChart(dataset);
            chartPanel = new ChartPanel(chart);
            this.setChart(chart);
        }

        private CategoryDataset createDataset() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(promo.getNbBacS(),"S","");
            dataset.addValue(promo.getNbBacSTI(),"STI","");
            dataset.addValue(promo.getNbBacES(),"ES","");
            dataset.addValue(promo.getNbBacSTG(),"STG","");
            dataset.addValue(promo.getNbBacEtranger(),"Etranger","");
            dataset.addValue(promo.getNbBacAutre(),"Autre","");
            
            
            return dataset;
//            final double[][] data = new double[][]{{10.0, 4.0, 15.0, 14.0}, {}};
//            return DatasetUtilities.createCategoryDataset("Series ", "Category ", data);
        }

        private JFreeChart createChart(final CategoryDataset dataset) {
            final JFreeChart chart = ChartFactory.createBarChart3D(
                    "STATISTIQUES BAC", // chart title
                    "Types de BAC", // domain axis label
                    "Nb étudiants", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );

            final CategoryPlot plot = chart.getCategoryPlot();
            final CategoryAxis axis = plot.getDomainAxis();
            axis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
            );
            final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            return chart;

        }

    }
    
    

}
