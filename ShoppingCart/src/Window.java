import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
    public Window(String title) throws IOException {
        init();
        setTitle(title);
        setBackground(Color.white);
        setSize(480, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void init() throws IOException {

        Container con = getContentPane();
        boolean fileflag =false;
        goods[] shopCar = new goods[100];
        goods[] ShangPin = new goods[100];
        Users user = new Users();
        int temp=0;
        user.SignIn(temp);
        File file_dir = new File(".\\Users\\"+user.id);
        if(!file_dir.exists()){
            file_dir.mkdir();
        }

        String filepath = ".\\Users\\"+user.id+"\\cart.txt";

        File file_user = new File(filepath);
        try{
            if(!file_user.exists()){
                file_user.createNewFile();
                fileflag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (fileflag==true){
            BufferedWriter writer = Files.newBufferedWriter(Path.of(filepath));
            for(int i = 0; i<ShangPin.length; i++){
                if(String.valueOf(ShangPin[i])=="null"){
                    break;
                }
                writer.write(String.valueOf(ShangPin[i]));
                writer.newLine();
            }
            writer.close();
        }

        List<goods> goodsList = new ArrayList<>();
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader(".\\GOODS"))){
            String line;
            int count=0;
            while ((line = bufferedReader.readLine()) != null){

                String[] data = line.split(",");
                goods Goods = new goods();
                int id = Integer.parseInt(data[0].trim());
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int buyNumber = Integer.parseInt(data[3]);

                Goods.setId(id);
                Goods.setName(name);
                Goods.setPrice(price);
                Goods.setBuyNumber(buyNumber);
                goodsList.add(Goods);
                ShangPin[count] = Goods;
                count++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }


        JTextArea notice = new JTextArea(8,40);
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItemSignIn;
        menuBar = new JMenuBar();
        menu = new JMenu("菜单");
        menuItemSignIn= new JMenuItem("SignIn",new ImageIcon("signin.jpg"));

        menuItemSignIn.setAccelerator(KeyStroke.getKeyStroke("Ctrl+S"));

        menu.add(menuItemSignIn);
        setLayout(new FlowLayout());
        JTextField User_text,Mingling;
        User_text = new JTextField(10);
        Mingling = new JTextField(10);
        notice.setText("————————————————————————\n用户已登录："+user.id+"\n请您选择如下命令进行操作：\n添加商品到购物车：add\n查询购物车中的商品：query\n结算购买商品的金额：pay\n请输入您的命令：\n");
        JLabel U_t;
        U_t = new JLabel("用户");
        menuBar.add(menu);
        menuBar.setSize(50,20);
        menuBar.setBackground(Color.lightGray);
        setJMenuBar(menuBar);
        //菜单的编写
        menuItemSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame JFSingIn = new JFrame();
                JFSingIn.setTitle("登陆界面");
                JFSingIn.setSize(300,100);
                JFSingIn.setVisible(true);
                JFSingIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JFSingIn.setResizable(false);


                JPanel JpSingIn = new JPanel();
                JLabel UserName_Label=new JLabel("用户名");
                JButton SignIn_Button = new JButton("登录");
                JTextField UserName = new JTextField(10);
                JFSingIn.add(JpSingIn);

                JpSingIn.add(UserName_Label);
                JpSingIn.add(UserName);
                JpSingIn.add(SignIn_Button);

                SignIn_Button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int UserId=Integer.parseInt(UserName.getText());
                        User_text.setText(String.valueOf(UserId));
                        user.SignIn(UserId);
                    }
                });
                int UserId = user.id ;
                boolean fileflag2 =false;
                File file_dir2 = new File(".\\Users\\"+user.id);
                if(!file_dir2.exists()){
                    file_dir2.mkdir();
                }

                String filepath = ".\\Users\\"+user.id+"\\cart.txt";

                File file_user1 = new File(".\\Users\\"+user.id+"\\cart.txt");
                try{
                    if(!file_user1.exists()){
                        file_user1.createNewFile();
                        fileflag2=true;
                    }
                }catch (Exception y){
                    y.printStackTrace();
                }
                if (fileflag2==true){
                    BufferedWriter writer;
                    try {
                        writer = Files.newBufferedWriter(Path.of(".\\Users\\"+user.id+"\\cart.txt"));

                    for(int i = 0; i<ShangPin.length; i++){
                        if(String.valueOf(ShangPin[i])=="null"){
                            break;
                        }
                        writer.write(String.valueOf(ShangPin[i]));
                        writer.newLine();
                        writer.close();
                    }
                    } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                }
                List<goods> goodsList3 = new ArrayList<>();
                try(BufferedReader bufferedReader= new BufferedReader(new FileReader(filepath))){
                    String line;
                    int count=0;
                    while ((line = bufferedReader.readLine()) != null){

                        String[] data = line.split(",");
                        goods Goods = new goods();
                        int id = Integer.parseInt(data[0].trim());
                        String name = data[1];
                        double price = Double.parseDouble(data[2]);
                        int buyNumber = Integer.parseInt(data[3]);

                        Goods.setId(id);
                        Goods.setName(name);
                        Goods.setPrice(price);
                        Goods.setBuyNumber(buyNumber);
                        goodsList3.add(Goods);
                        shopCar[count] = Goods;
                        count++;
                    }
                }catch(IOException x){
                    x.printStackTrace();
                }
            }



        });
        int UserId = user.id;
        //
        JPanel lp1;

        JSplitPane conrowupper;

        String filepath2 = ".\\Users\\"+user.id+"\\cart.txt";
        conrowupper= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        conrowupper.setLeftComponent(lp1=new JPanel());
        conrowupper.setRightComponent(new JScrollPane(notice));

        con.add (conrowupper);

        conrowupper.setContinuousLayout(false);


        JButton Add=new JButton("Add");

        notice.setEditable(false);
        User_text.setEditable(false);
        JButton QueRenML,ChaKanSP;
        QueRenML = new JButton("确认");
        ChaKanSP = new JButton("查看商品");
        lp1.add(U_t);
        lp1.add(User_text);
        con.add(Mingling);
        con.add(QueRenML);
        con.add(ChaKanSP);
        con.add(Add);

        User_text.setText(String.valueOf(UserId));
        ChaKanSP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notice.append("===============查询购物车信息如下============\n");
                notice.append("编号\t名称\t\t价格\n");
                for(int i = 0 ; i < ShangPin.length; i++)
                {
                    goods s = ShangPin[i];
                    if(ShangPin[i]!=null)
                    {
                        //展示这个商品类型
                        notice.append(s.id + "\t" + s.name +"\t\t" + s.price+"\n");
                    }
                    else
                        break;
                }
                notice.append("输入back返回\n");
            }
        });
        QueRenML.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Mingling.getText().equals("add")){
                    notice.append("请输入货品号码\n");
                    Add.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            boolean flag = false;
                            for(int i = 0 ; i<ShangPin.length ; i++){
                                if(String.valueOf(ShangPin[i])=="null"){
                                    break;
                                }
                                if( ShangPin[i].id==Integer.parseInt(Mingling.getText())){
                                    shopCar[i].buyNumber=shopCar[i].buyNumber+1;
                                    flag = true;
                                    notice.append("修改成功");
                                    break;
                                }
                            }
                            if(flag==false){
                                notice.append("未找到商品\n");
                            }

                            try{
                            BufferedWriter writer = Files.newBufferedWriter(Path.of(filepath2));

                            for(int i = 0; i<shopCar.length; i++){
                                if(String.valueOf(shopCar[i])=="null"){
                                    break;
                                }
                                writer.write(String.valueOf(shopCar[i]));
                                writer.newLine();

                            }
                            writer.close();}catch (IOException x){
                                x.printStackTrace();
                            }


                            List<goods> goodsList4 = new ArrayList<>();
                            try(BufferedReader bufferedReader= new BufferedReader(new FileReader(filepath2))){
                                String line;
                                int count=0;
                                while ((line = bufferedReader.readLine()) != null){

                                    String[] data = line.split(",");
                                    goods Goods = new goods();
                                    int id = Integer.parseInt(data[0].trim());
                                    String name = data[1];
                                    double price = Double.parseDouble(data[2]);
                                    int buyNumber = Integer.parseInt(data[3]);

                                    Goods.setId(id);
                                    Goods.setName(name);
                                    Goods.setPrice(price);
                                    Goods.setBuyNumber(buyNumber);
                                    goodsList4.add(Goods);
                                    shopCar[count] = Goods;
                                    count++;
                                }
                            }catch(IOException x){
                                x.printStackTrace();
                            }
                        }
                    });
                }
                else if(Mingling.getText().equals("query")){
                    notice.append("===============查询购物车信息如下============\n");
                    notice.append("编号\t名称\t\t价格\t\t数量\n");
                    for(int i = 0 ; i < shopCar.length; i++)
                    {

                        if(shopCar[i]!=null)
                        {
                            goods s = shopCar[i];
                            //展示这个商品类型
                            notice.append(s.id + "\t" + s.name +"\t\t" + s.price +"\t\t"+s.buyNumber+"\n");
                        }
                        else
                            break;
                    }
                    notice.append("输入back返回\n");
                }
                else if(Mingling.getText().equals("pay")){
                    double money = 0;
                    for(int i = 0; i <shopCar.length; i++)
                    {
                        goods s = shopCar[i];
                        if(s != null)
                        {
                            money += s.price *s.buyNumber ;
                        }
                        else
                            break;
                    }
                    notice.append("共需要"+money+"元\n");
                }
                else if(Mingling.getText().equals("back")){
                    notice.setText("————————————————————————\n用户已登录："+user.id+"\n请您选择如下命令进行操作：\n添加商品到购物车：add\n查询购物车中的商品：query\n结算购买商品的金额：pay\n请输入您的命令：\n");
                }
            }
        });






    }




}

