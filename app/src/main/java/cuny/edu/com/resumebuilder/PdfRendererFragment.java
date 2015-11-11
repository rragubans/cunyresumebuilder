package cuny.edu.com.resumebuilder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cuny.edu.com.resumebuilder.pdf.ResumeGenerator;


public class PdfRendererFragment extends Fragment  {

    private PDFView pdfView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View view     = inflater.inflate(R.layout.pdfrenderer_fragment, container, false);
        final PDFView pdfView = (PDFView) view.findViewById(R.id.pdfview);

        View submitButton = view.findViewById(R.id.buttonViewResults);

        ((Button) submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ResumeGenerator resumeGenerator = new ResumeGenerator();
                ResumeInformation resumeInformation = SQLLiteHelper.getInstance().findResume();
                resumeGenerator.generateResume(resumeInformation, getContext());

                String pdfName = null;
                String fileName = getContext().getFilesDir().getPath();

                fileName = "/data/data/cuny.edu.com.resumebuilder/files/resume.pdf";

                try {

                    File input = new File(fileName);
                    System.out.println("Loading pdf");
                    pdfView.fromFile(input)
                            .defaultPage(1)
                            .showMinimap(false)
                            .enableSwipe(true)
                            .onLoad(new OnLoadCompleteListener() {
                                @Override
                                public void loadComplete(int nbPages) {

                                }
                            })
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {

                                }
                            })
                            .load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return view;
    }
}
