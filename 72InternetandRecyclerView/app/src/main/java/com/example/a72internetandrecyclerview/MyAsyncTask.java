package com.example.a72internetandrecyclerview;

import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MyAsyncTask extends AsyncTask<Void, Void, LinkedList<MyItemData>> {
    private RecyclerView recyclerView = null;

    public MyAsyncTask(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected LinkedList<MyItemData> doInBackground(Void... voids) {
        LinkedList<MyItemData> data = new LinkedList<>();
        try {
            // B1. Tao http connection
            URL url = new URL("https://tuoitre.vn/rss/tin-moi-nhat.rss");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // B2. Set cac tham so cho connection
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(15000);

            // B3. Ket noi thanh cong thi doc du lieu
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document xmlDoc = builder.parse(is);
                NodeList itemNodes = xmlDoc.getElementsByTagName("item");
                for (int i = 0; i < itemNodes.getLength(); i++) {
                    Node itemNode = itemNodes.item(i);
                    MyItemData itemData = new MyItemData();

                    Node title = ((Element) itemNode).getElementsByTagName("title").item(0);
                    String strTitle = title.getTextContent();
                    itemData.setTitle(strTitle);

                    Node desc = ((Element) itemNode).getElementsByTagName("description").item(0);
                    String strDesc = desc.getTextContent(); // da bo '<![CDATA[ ' o dau va ' ]]>' o cuoi roi
                    int start = strDesc.indexOf("<img src=\"");
                    int end = strDesc.indexOf("\" />", start);
                    // Log.d("abc", strDesc.substring(start + 10, end));
                    itemData.setThumb(strDesc.substring(start + 10, end));

                    start = strDesc.indexOf("</a>");
                    end = strDesc.length();
                    // Log.d("abc", strDesc.substring(start + 5, end));
                    itemData.setSummary(strDesc.substring(start + 5, end));

                    data.add(itemData);
                }
            }

            // Hien thi du lieu len RecyclerView
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(LinkedList<MyItemData> data) {
        super.onPostExecute(data);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }
}
