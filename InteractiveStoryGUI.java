import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InteractiveStoryGUI extends JFrame {

    private StoryManager manager;
    private Player player;
    private Story currentStory;

    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JPanel choicePanel;

    public InteractiveStoryGUI() {
        manager = new StoryManager();
        player = new Player();

        createStories();

        currentStory = manager.getStory("S1");

        setTitle("Sistem Manajemen Cerita Interaktif");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        choicePanel = new JPanel();
        choicePanel.setLayout(new GridLayout(0, 1));

        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        add(choicePanel, BorderLayout.SOUTH);

        updateStory();

        setVisible(true);
    }

    private void updateStory() {
        titleLabel.setText(currentStory.getId());
        descriptionArea.setText(currentStory.getDescription());

        choicePanel.removeAll();

        ArrayList<Choice> choices = currentStory.getChoices();

        for (Choice choice : choices) {
            JButton button = new JButton();
            button.setText(choice.getText());

            button.addActionListener(e -> {
                String next = choice.choose(player);

                if (next.equals("END")) {
                    JOptionPane.showMessageDialog(this,
                            "Skor Moral: " + player.getMoralScore() +
                                    "\n" + EndingEvaluator.evaluate(player));
                    System.exit(0);
                } else {
                    currentStory = manager.getStory(next);
                    updateStory();
                }
            });

            choicePanel.add(button);
        }

        revalidate();
        repaint();
    }

    private void createStories() {

        Story s1 = new Story("S1", "Kesadaran",
                "Kamu terbangun di hutan lebat tanpa ingatan yang jelas.");
        s1.addChoice(new MoralChoice("Bangkit dan mengamati sekitar", 1, "S2"));
        s1.addChoice(new MoralChoice("Tetap berbaring", -1, "S3"));

        Story s2 = new Story("S2", "Sekitar",
                "Kamu melihat jalan setapak, sungai kecil, dan kabut tebal.");
        s2.addChoice(new MoralChoice("Ikuti jalan setapak", 0, "S4"));
        s2.addChoice(new MoralChoice("Menuju sungai", 1, "S5"));
        s2.addChoice(new MoralChoice("Masuk ke kabut", -1, "S6"));

        Story s3 = new Story("S3", "Kelelahan",
                "Tubuhmu semakin lemah.");
        s3.addChoice(new MoralChoice("Memaksa berdiri", -1, "S2"));
        s3.addChoice(new MoralChoice("Menyerah", -3, "END1"));

        Story s4 = new Story("S4", "Jalan Lama",
                "Jalan ini tampak jarang dilewati.");
        s4.addChoice(new MoralChoice("Teruskan", 0, "S7"));
        s4.addChoice(new MoralChoice("Kembali", 0, "S2"));

        Story s5 = new Story("S5", "Sungai Dangkal",
                "Kamu melihat seseorang terseret arus.");
        s5.addChoice(new MoralChoice("Menolongnya", 2, "S8"));
        s5.addChoice(new MoralChoice("Mengabaikan", -1, "S9"));

        Story s6 = new Story("S6", "Kabut",
                "Kabut membuatmu tersesat.");
        s6.addChoice(new MoralChoice("Maju tanpa arah", -1, "S10"));
        s6.addChoice(new MoralChoice("Mundur perlahan", 0, "S2"));

        Story s7 = new Story("S7", "Pondok Kayu",
                "Ada pondok tua dengan cahaya redup.");
        s7.addChoice(new MoralChoice("Masuk", 0, "S11"));
        s7.addChoice(new MoralChoice("Lewati", 0, "S12"));

        Story s8 = new Story("S8", "Orang Selamat",
                "Dia berterima kasih dan menawarkan informasi.");
        s8.addChoice(new MoralChoice("Mendengarkan", 1, "S13"));
        s8.addChoice(new MoralChoice("Menolak", 0, "S9"));

        Story s9 = new Story("S9", "Kesendirian",
                "Kamu melanjutkan perjalanan sendiri.");
        s9.addChoice(new MoralChoice("Masuk hutan dalam", -1, "S12"));
        s9.addChoice(new MoralChoice("Ikuti sungai", 1, "S14"));

        Story s10 = new Story("S10", "Tersesat",
                "Kamu berputar-putar tanpa arah.");
        s10.addChoice(new MoralChoice("Tenangkan diri", 1, "S2"));
        s10.addChoice(new MoralChoice("Panika", -2, "END2"));

        Story s11 = new Story("S11", "Dalam Pondok",
                "Ada makanan dan peta lama.");
        s11.addChoice(new MoralChoice("Ambil secukupnya", 1, "S13"));
        s11.addChoice(new MoralChoice("Ambil semuanya", -1, "S15"));

        Story s12 = new Story("S12", "Hutan Gelap",
                "Suara aneh terdengar di sekelilingmu.");
        s12.addChoice(new MoralChoice("Mengintai", -1, "S16"));
        s12.addChoice(new MoralChoice("Lari", 0, "S14"));

        Story s13 = new Story("S13", "Informasi",
                "Kamu mengetahui arah keluar hutan.");
        s13.addChoice(new MoralChoice("Ikuti petunjuk", 1, "S17"));
        s13.addChoice(new MoralChoice("Cari jalan sendiri", 0, "S12"));

        Story s14 = new Story("S14", "Sungai Besar",
                "Sungai ini lebih deras.");
        s14.addChoice(new MoralChoice("Menyebrangi", -1, "S18"));
        s14.addChoice(new MoralChoice("Mengikuti arus", 1, "S19"));

        Story s15 = new Story("S15", "Keserakahan",
                "Perbekalanmu rusak.");
        s15.addChoice(new MoralChoice("Bertahan", -2, "END3"));

        Story s16 = new Story("S16", "Makhluk Liar",
                "Makhluk buas mengintaimu.");
        s16.addChoice(new MoralChoice("Melawan", -2, "END4"));
        s16.addChoice(new MoralChoice("Menghindar", 0, "S14"));

        Story s17 = new Story("S17", "Persimpangan",
                "Ada tiga jalan berbeda.");
        s17.addChoice(new MoralChoice("Jalan berbatu", 0, "S20"));
        s17.addChoice(new MoralChoice("Jalan terbuka", 1, "S21"));
        s17.addChoice(new MoralChoice("Jalan sempit", -1, "S22"));

        Story s18 = new Story("S18", "Arus Kuat",
                "Kamu hampir tenggelam.");
        s18.addChoice(new MoralChoice("Berenang keras", -1, "S19"));
        s18.addChoice(new MoralChoice("Menyerah", -3, "END5"));

        Story s19 = new Story("S19", "Tepi Sungai",
                "Kamu selamat ke tepi sungai.");
        s19.addChoice(new MoralChoice("Beristirahat", 1, "S21"));
        s19.addChoice(new MoralChoice("Lanjutkan", 0, "S23"));

        Story s20 = new Story("S20", "Jalan Berbatu",
                "Jalan sulit tapi aman.");
        s20.addChoice(new MoralChoice("Teruskan", 0, "S23"));

        Story s21 = new Story("S21", "Wilayah Terbuka",
                "Kamu menemukan ladang kosong.");
        s21.addChoice(new MoralChoice("Mencari bantuan", 1, "S24"));
        s21.addChoice(new MoralChoice("Mendirikan kemah", 0, "S25"));

        Story s22 = new Story("S22", "Jalan Sempit",
                "Jalan ini berbahaya.");
        s22.addChoice(new MoralChoice("Terus maju", -1, "S26"));
        s22.addChoice(new MoralChoice("Kembali", 0, "S17"));

        Story s23 = new Story("S23", "Dataran Tinggi",
                "Kamu bisa melihat kota di kejauhan.");
        s23.addChoice(new MoralChoice("Menuju kota", 2, "S27"));
        s23.addChoice(new MoralChoice("Menjauh", -1, "S28"));

        Story s24 = new Story("S24", "Pertemuan Penduduk",
                "Penduduk desa menyambutmu.");
        s24.addChoice(new MoralChoice("Bergabung", 2, "END6"));
        s24.addChoice(new MoralChoice("Pergi", 0, "S25"));

        Story s25 = new Story("S25", "Kemah",
                "Malam yang tenang.");
        s25.addChoice(new MoralChoice("Beristirahat", 1, "S23"));

        Story s26 = new Story("S26", "Jurang",
                "Langkahmu tergelincir.");
        s26.addChoice(new MoralChoice("Bertahan", -2, "END7"));

        Story s27 = new Story("S27", "Kota Besar",
                "Kamu tiba di kota besar.");
        s27.addChoice(new MoralChoice("Memulai hidup baru", 3, "END8"));
        s27.addChoice(new MoralChoice("Mencari masa lalu", 1, "END9"));

        Story s28 = new Story("S28", "Pengasingan",
                "Kamu memilih hidup jauh dari peradaban.");
        s28.addChoice(new MoralChoice("Bertahan hidup", 0, "END10"));

        manager.addStory(s1);  manager.addStory(s2);  manager.addStory(s3);
        manager.addStory(s4);  manager.addStory(s5);  manager.addStory(s6);
        manager.addStory(s7);  manager.addStory(s8);  manager.addStory(s9);
        manager.addStory(s10); manager.addStory(s11); manager.addStory(s12);
        manager.addStory(s13); manager.addStory(s14); manager.addStory(s15);
        manager.addStory(s16); manager.addStory(s17); manager.addStory(s18);
        manager.addStory(s19); manager.addStory(s20); manager.addStory(s21);
        manager.addStory(s22); manager.addStory(s23); manager.addStory(s24);
        manager.addStory(s25); manager.addStory(s26); manager.addStory(s27);
        manager.addStory(s28);

        manager.addStory(new Story("END1", "Akhir", "Kamu kehabisan tenaga."));
        manager.addStory(new Story("END2", "Akhir", "Kepanikan menghancurkanmu."));
        manager.addStory(new Story("END3", "Akhir", "Keserakahan membawa kehancuran."));
        manager.addStory(new Story("END4", "Akhir", "Makhluk buas mengalahkanmu."));
        manager.addStory(new Story("END5", "Akhir", "Kamu tenggelam."));
        manager.addStory(new Story("END6", "Akhir Bahagia", "Kamu hidup damai di desa."));
        manager.addStory(new Story("END7", "Akhir", "Kamu jatuh ke jurang."));
        manager.addStory(new Story("END8", "Akhir Gemilang", "Kamu sukses di kota."));
        manager.addStory(new Story("END9", "Akhir Haru", "Kamu menemukan jati diri."));
        manager.addStory(new Story("END10", "Akhir Sunyi", "Kamu hidup menyendiri."));
    }

    public static void main(String[] args) {
        new InteractiveStoryGUI();
    }
}
