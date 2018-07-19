package javase07.task1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class first_synchron {

    private static List<Transaction> getXML(String path) throws ParserConfigurationException, IOException, SAXException {
        File XML = new File(path);

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(XML);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("transaction");
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                transaction = new Transaction();
                transaction.setSrc_card(eElement.getElementsByTagName("src_card").item(0).getTextContent());
                transaction.setDst_card(eElement.getElementsByTagName("dst_card").item(0).getTextContent());
                transaction.setAmount(eElement.getElementsByTagName("amount").item(0).getTextContent());
                transaction.setDate(eElement.getElementsByTagName("date").item(0).getTextContent());

                transactions.add(transaction);
            }
        }
        return transactions;
    }

    private static final Object obj1 = new Object();

    private static List<Transaction> getXMLSynch(String path) throws IOException, SAXException, ParserConfigurationException {
        List<Transaction> transactions = null;
        synchronized (obj1) {
            transactions = getXML(path);
        }
        return transactions;
    }

    private static Lock obj2 = new ReentrantLock();

    private static List<Transaction> getXMLCon(String path) throws IOException, SAXException, ParserConfigurationException {
        List<Transaction> transactions;
        obj2.lock();
        transactions = getXML(path);
        obj2.unlock();
        return transactions;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        List<Transaction> transactionsSynch = getXMLSynch(
                "C:\\Users\\Stern\\IdeaProjects\\task22\\src\\main\\resources\\transactions.xml");
        System.out.println(transactionsSynch.toString());
        List<Transaction> transactionsCon = getXMLCon(
                "C:\\Users\\Stern\\IdeaProjects\\task22\\src\\main\\resources\\transactions.xml");
        System.out.println(transactionsCon.toString());

    }
}
