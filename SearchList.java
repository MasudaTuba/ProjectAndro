package com.example.user.projectandro;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.SearchView;


public class SearchList extends AppCompatActivity  implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {


    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private int expandedlasttime=-1;
    private ArrayList<Parent> ParentList = new ArrayList<Parent>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        //display the list
        displayList();
        //expand all Groups

        myList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(expandedlasttime!=-1&&expandedlasttime!=groupPosition)
                {
                    myList.collapseGroup(expandedlasttime);
                }
                expandedlasttime=groupPosition;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {

        //display the list
        loadSomeData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(SearchList.this, ParentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

    }

    private void loadSomeData() {

        ArrayList<Child> ChildList = new ArrayList<Child>();
        Child Child = new Child("1","Women's rights in law Article 10 of the Constitution provides that steps shall be taken to\\n ensure participation of women in all spheres of national life.\n");
        ChildList.add(Child);
        Child = new Child("2"," Article 10 of the Constitution provides that steps shall be taken to\\n ensure participation of women in all spheres of national life.\n");
        ChildList.add(Child);


        Parent Parent = new Parent("Women's rights in law",ChildList);
        ParentList.add(Parent);

        ChildList = new ArrayList<Child>();
        Child = new Child("1","To fight the battle against domestic violence in Bangladesh, different Acts and sections has been commenced.the Penal Code 1860 has the following sections for the prevention of violence and violence against woman:286,312-338, 359-374,493-498 and 509.Nari o Shishu Nirjaton Daman Ain 2000 has clearly stated provisions of punishment for the crime of violence against domestic oppression on women and children.\n");
        ChildList.add(Child);


        Parent = new Parent("Family Violence",ChildList);
        ParentList.add(Parent);







        ChildList = new ArrayList<Child>();
        Child = new Child("1","Acid assaults are another prevalent form of violence that may well be a phenomenon unique to Bangladesh (there are anecdotal reports of a few cases of such assaults from other parts of the world). The first documented case of acid attack occurred in 1983 in Sylhet (Naripokkho Acid Log, 1997. Acid attacks usually occur when men want to take revenge for the refusal of proposals for sex or marriage, or when demands for dowry are not met or when there is a political clash. However men are also sometimes subject to acid attacks. ");
        ChildList.add(Child);

        Parent = new Parent("Acid Assaults",ChildList);
        ParentList.add(Parent);




        ChildList = new ArrayList<Child>();
        Child = new Child("1","According to Section 375 of the Bangladesh Penal Code, rape occurs when a man has intercourse with a woman of any age without her consent. Many instances of gang rape are also reported as well as rape followed by murder. Rape may occur in different form: marital rape, rape in armed conflicts, rape of women refugees, statutory rape, gang rape, and jackrolling or “recreational rape”. Sexual assault or abuse is any type of sexual activity that a person does not agree to, including:  Rape or attempted rape  Touching body or making touch someone else  Incent or sexual contact with a child  Someone watching or photography in sexual situations  Someone exposing his or her body. ");
        ChildList.add(Child);


        Parent = new Parent("Rape and sexual violence",ChildList);
        ParentList.add(Parent);




        ChildList = new ArrayList<Child>();
        Child = new Child("1","Custodial violence refers to violence that is directed towards anybody placed under State custody. State custody refers to government agents, such as the police or military personnel, other law enforcing agencies, as well as different shelters and vagrant homes run by the state machineries Custodial violence is indifferent to the nature of the alleged criminal activity under which women are apprehended. Women are vulnerable to abuse whether accused of petty theft, inappropriate sexual behavior or affiliation with a “wanted” criminal. The nature of the abuse ranges from physical or verbal harassment and humiliation to sexual and physical torture. There is a rule of not arresting women after sunset. However, often police misuse the Section 541 especially after sunset to arrest women and keep them in police custody. ");
        ChildList.add(Child);


        Parent = new Parent("Custodial Violence",ChildList);
        ParentList.add(Parent);



        ChildList = new ArrayList<Child>();
        Child = new Child("1","Both women and men are victims of murder at the hands of strangers as well as known People including family members. Women are often murdered by their closest relatives such as husband, brother, son and in-law as consequence of family quarrel, demand for land, polygamy, husbands extra-marital affair or remarriage, demands for dowry, failure to give birth to children especially a son. In many cases, women commit suicide when they cannot bear the pain of physical and mental torture as a consequence of rape, religion based community violence such as fatwa, dowry demands and abandonment. Both of these forms of violence violate the first clause of the Universal Declaration of Human Rights – the human right to life ");
        ChildList.add(Child);

        Parent = new Parent("Murder and Suicide",ChildList);
        ParentList.add(Parent);



        ChildList = new ArrayList<Child>();
        Child = new Child("1","Community violence occurs when community members collectively perpetrate violence on individuals of the same community. It is the outcome of a “community” decision to punish one of their members. ");
        ChildList.add(Child);


        Parent = new Parent("Community Violence",ChildList);
        ParentList.add(Parent);




        ChildList = new ArrayList<Child>();
        Child = new Child("1","Trafficking of women and children is a significant problem in developing countries around the globe and particularly in south Asia.  In the absence of social protection economic security and legal support ,an alarming number of women and children from the poor ,marginalized section of community become easy victims of trafficking Bangladeshi women and children are trafficked both within the country and internationally .The never ending demand for the women and children make trafficking  highly profitable business. Victims of Trafficking are generally trafficked for forced prostitution, but sometimes also for other purposes such as organ transplants and slave labor. ");
        ChildList.add(Child);


        Parent = new Parent("Trafficking in women",ChildList);
        ParentList.add(Parent);



        ChildList = new ArrayList<Child>();
        Child = new Child("1","According to Islamic teaching, Fatwa is a religious edict based on Islamic principles pronounces by a religious scholar. In Bangladesh this notion has been totally misconceived and is used by Half-Educated Village mullahs, who actually are not scholars in Islam. These Mullahs through the informal Village Justice System(Salish) punish women for so-called anti-social or immoral Activities. In almost, all the cases women are bought before the Salish simply for their involvement in extra-marital affairs, marrying a man from different religion, giving birth to a child before marriage, complications due to oral divorce pronounced to a woman by her husband and so on. Although these punishments are not legal parse because the mullahs exert considerable autonomy and power in the rural areas, the punishment is generally carried out against the helpless women. Peoples lack of knowledge about the law and religion, poor education and absence of social awareness are the key factors which allow fatwa to be issues. \n" +
                " ");
        ChildList.add(Child);


        Parent = new Parent("Victimization by Fatwa",ChildList);
        ParentList.add(Parent);




        ChildList = new ArrayList<Child>();
        Child = new Child("1","Wife beating is the most commonly occurring Act of domestic violence Bangladesh. In Bangladesh it is common knowledge that husband exert their authority and physically assault wives for even minor mistakes such as unsatisfactory meal, an untidy room, a conversation with another man or any act of disagreement. Men have been socially conditioned to genuinely believe in their own superiority. From childhood they are treated differently from their sisters. They grow to believe that they are more valuable and more deserving than women that their opinions and views should have more weight than any woman’s. In This way, men are able to delude themselves into believing that abuse of their wives amounts to a religious duty and they are completely justified in their actions.");
        ChildList.add(Child);

        Parent = new Parent("Torture by Husband",ChildList);
        ParentList.add(Parent);





        ChildList = new ArrayList<Child>();
        Child = new Child("1","The practice of dowry demand (joutuk) is not deeply rooted in Bengali Muslim tradition but has emerged as a major social evil in recent years. Generally, dowry means the property that the bride’s family gives to the groom or his family upon marriage. In Bangladesh law, dowry has been given an extended meaning. Whatever is presented whether before or after marriage under demand, Compulsion or pressure as consideration for the marriage can be said to be dowry. Rising Unemployment has contributed to the phenomenon as more and more young men are unable to find employment ,their families use marriage and dowry demand as a source of income, prospective grooms and their families demand large sums of money or property from the would be brides family as a pre-condition to the marriage agreement. Although dowry demand is illegal the practice persists in the rural communities. For example, .the women has acid throw on her face, in burnt, severely beaten and in some even murdered. ");
        ChildList.add(Child);


        Parent = new Parent("Dowry related Violence<",ChildList);
        ParentList.add(Parent);



        ChildList = new ArrayList<Child>();
        Child = new Child("1","Parvin was kidnapped by Nuzrul Islam. After a few days, Nazrul Islam and his family torturing her both mentally and physically for dowry.Parvin father was not alive and her mother was not rich but nonetheless managed to raise Tk.40, 000.Parvin husband and in-laws were not satisfied with the amount and continued torturing her with 50,000.On September 2014,Nuzrul Islam with the assistance from his sister’s husband throttled parvin to death and hang her from the ceiling for the make it look like a case of suicide. Bangladesh Mahilaparished assisted in the prosecution of the trial in flavor of the complainant. Nazrul Islam was sentenced to imprisonment for life .However; it was not possible to arrest him. It was rumored that he had absconded to India");
        ChildList.add(Child);


        Parent = new Parent("Case Reference",ChildList);
        ParentList.add(Parent);




        ChildList = new ArrayList<Child>();
        Child = new Child("1","For the most part, married women in Bangladesh are not aware of their own sexual and re-productive rights, and have only limited control over their own bodies. A Women’s freedom of choice regarding sexual intercourse, birth control, Pregnancy, pre-natal care and abortion is restriction by the collaborative decision-making of her husband and his family. In this situation, the husband is the aggressor and the wife merely a passive participant. Similarly, A wife personal convictions on birth control and family planning are irrelevant in decision- making. Only 5% of the re-productive aged women access existing heath care facilities. In fact, 37%of all death of reproductive aged women is due to maternity related issues In Bangladesh, three women die every hour because of maternal related complexities and nine million whose lives have been saved following maternity related complications continue to suffer from other long-term diseases. ");
        ChildList.add(Child);


        Parent = new Parent("High-risk pregnancy",ChildList);
        ParentList.add(Parent);



        ChildList = new ArrayList<Child>();
        Child = new Child("1","Eve-teasing is a great concern today in the discussion of the violation against women in Bangladesh .Eve-teasing is not new problem in Bangladesh .However ,recently  it’s a magnitude has increased and it  has become a big crisis for the country .Nowadays ,it is dealt with seriously by different types of professionals, especially social activities and academics. The term “Eve-teasing” is an activity where the girls are teased by the boys .It means ,when a boy or a group of boys try to disturb a girl or girls by yelling ,showing, sexual gesture and instrument ,Obstructing, Offering bad proposal etc. ");
        ChildList.add(Child);


        Parent = new Parent("Eve-teasing",ChildList);
        ParentList.add(Parent);



        ChildList = new ArrayList<Child>();
        Child = new Child("1","Whether out of choice or out of compulsion, most of the women who are involved in the work place in all vital sectors of country’s economy may fall in the unorganized sectors. They are ill-paid but don’t leave the job due to increasing unemployment.  This need for survival drives to rape situations .Harassment at work place is all pervasive .Women from all backgrounds are attacks each year at work .Among women, murder is the leading cause of death from a work place injury .Sometimes Women are attacked during a robbery .Usually, though women are hurt by someone they know, like a co-workers, customer, client or patient. Sometimes attacks are the result of domestic the violation that spills over into the workplace. ");
        ChildList.add(Child);


        Parent = new Parent("Harassment at work places",ChildList);
        ParentList.add(Parent);

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }
}
