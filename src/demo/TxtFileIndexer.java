/**
 * 
 */
package demo;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory; 
/** 
* This class demonstrate the process of creating index with Lucene 
* for text files 
*/ 
public class TxtFileIndexer { 
     public static void main(String[] args) throws Exception{ 
     //indexDir is the directory that hosts Lucene's index files 
//     File   indexDir = new File("D:\\luceneIndex"); 
     //dataDir is the directory that hosts the text files that to be indexed 
//     File   dataDir  = new File("D:\\luceneData"); 
//     Directory dataDir = new Directory("D:\\luceneData"); 

     Analyzer luceneAnalyzer = new StandardAnalyzer(); 
     
     Directory directory = new RAMDirectory();
     IndexWriterConfig config = new IndexWriterConfig(luceneAnalyzer);
//     File[] dataFiles  = dataDir.listFiles(); 
     IndexWriter indexWriter = new IndexWriter(directory,config); 
     long startTime = new Date().getTime(); 
//     for(int i = 0; i < dataFiles.length; i++){ 
//          if(dataFiles[i].isFile() && dataFiles[i].getName().endsWith(".txt")){
//               System.out.println("Indexing file " + dataFiles[i].getCanonicalPath()); 
//               Document document = new Document(); 
//               Reader txtReader = new FileReader(dataFiles[i]); 
//               document.add(Field.Text("path",dataFiles[i].getCanonicalPath())); 
//               document.add(Field.Text("contents",txtReader)); 
//               indexWriter.addDocument(document); 
//          } 
//     } 
     Document doc = new Document();
     String text = "This is the text to be indexed.";
     doc.add(new Field("fieldname", text, TextField.TYPE_STORED));     
     
     indexWriter.addDocument(doc);
//     indexWriter.optimize(); 
     indexWriter.close(); 
     long endTime = new Date().getTime(); 
         
     System.out.println("It takes " + (endTime - startTime) 
         + " milliseconds to create index for the files in directory ");     
     
     
     
     
     
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	    // Parse a simple query that searches for "text":
	    QueryParser parser = new QueryParser("fieldname", luceneAnalyzer);
	    Query query = parser.parse("text");
	    ScoreDoc[] hits = isearcher.search(query,1000).scoreDocs;
	    System.out.println(hits.length);
	    // Iterate through the results:
	    for (int i = 0; i < hits.length; i++) {
	      Document hitDoc = isearcher.doc(hits[i].doc);
	      System.out.println("This is the text to be indexed. ==" + hitDoc.get("fieldname"));
	    }
	    ireader.close();
	    directory.close();
     } 
}
