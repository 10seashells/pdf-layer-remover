package pdf_stuff.pdf_layer_remover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.layer.PdfLayerCollection;

/**
 * Prints names of layers in a pdf file 
 * and allows user to select which ones to delete.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/* Command line set up and parse */
    	Options options = new Options();

    	options.addOption(
    		Option.builder("i")
    		.longOpt("infile")
	        .hasArg()
	        .desc("input file path")
	        .required()
	        .build());
    	
    	options.addOption(
        	Option.builder("o")
        	.longOpt("outfile")
    	    .hasArg()
    	    .desc("output file path")
    	    .required()
    	    .build());
    	
    	String infile = "";
    	String outfile = "";
    		
    	CommandLineParser parser = new DefaultParser();
		try {
	    	CommandLine cmd  = parser.parse(options, args);
	    	infile = (String) cmd.getParsedOptionValue("infile");
	    	outfile = (String) cmd.getParsedOptionValue("outfile");

		} catch (ParseException e) {
			e.printStackTrace();
		}  
        
		/* Pdf set up */
    	PdfDocument pdf = new PdfDocument();
    	pdf.loadFromFile(infile);
    	PdfLayerCollection layers = pdf.getLayers();
    	// print the layer names
        for (int i=0; i<layers.getCount();i++) {
        	System.out.println(i+": "+layers.get(i).getName());
        }
        
        /* User input */
        System.out.println("Please enter the numbers of layers you would like to remove.");
        System.out.println("Type \"save\" to save your pdf and exit");
        ArrayList<Integer> layersToRemove = new ArrayList<Integer>();
    	Scanner sc = new Scanner(System.in);
        while(layers.getCount()>0) {
        	if (sc.hasNextInt()) {
        		layersToRemove.add(sc.nextInt());
        	// if input is not an int, end of list
        	}else{
        		break;
        	}
        }
        sc.close();
        
        /* remove layers and save */
        // remove in reverse numerical order otherwise indices will be wrong
        Collections.sort(layersToRemove);
        Collections.reverse(layersToRemove);
        for (int i : layersToRemove) {
        	layers.removeLayer(layers.get(i));
        }

        pdf.saveToFile(outfile);
        pdf.close();
    }
}
