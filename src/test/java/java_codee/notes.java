package java_codee;

public class notes {

    /*
    Today is 12/5/2019
    Agenda:
        Extent report with TestNG
        Taking a screen-shot
        Break:)))))
        Git/github: conflicts, stashing changes
#############################################
    The problem was in explicit + implicit wait. Try to use only explicit wait.
        @Test(description = "Verify that page subtitle is equals to 'All Calendar Events'")
    public void test1() {
        LoginPage loginPage = new LoginPage(); //login page object
        loginPage.login("storemanager85", "UserUser123");
        loginPage.navigateTo("Activities", "Calendar Events");
        String expectedSubtitle = "All Calendar Events";
        String actualSubTitle = loginPage.getPageSubTitle();
        Assert.assertEquals(actualSubTitle, expectedSubtitle);
    }
 To avoid copy/pasting we implemented Page Object Model: idea is to create corresponded java class for each page of application.
    Login page in vytrack = LoginPage.java under pages package
Another enhancement is a TestBase.
TestBase is a super class for all tests classes. It contains setup and teardown steps/stages.
Don't forget to extend test base class in every test class:
    public class NewCalendarEventsTests extends TestBase {
//this class will be a test foundation for all test classes
//we will put here only before and after parts
//In this way before and after methods will be the same
//Every test class will extend testbase class
public abstract class TestBase {
    @BeforeMethod
    public void setup(){
        String url = ConfigurationReader.getProperty("url");
        Driver.get().get(url);
    }
    @AfterMethod
    public void teardown(){
        Driver.close();
    }
}
WHAT'S THE DIFFERENCE BETWEEN TESTBASE AND BASEPAGE CLASSES?
BasePage ---> super class for page classes, all classes that are located under pages package.
    public class LoginPage extends BasePage {
TestBase ---> super class for test classes, all classes that are located under tests package

    public class LoginTests extends TestBase {
The role of TestBase class is to setup everything before test (initialize webdriver, provide browser configuration, connect to data base, initialize report) and cleanup after test (close browser, close connection to db, flush report, take a screenshot if test failed)
The role of BasePage class is to store common web elements and methods of application.  Since page class is a webelemnts repository, we can have parent class that will store common webelements. Also, this class initialize PageFactory.
The role of PageFactory is to provide easier way for finding elements (@FindBy annotation)
Don't forget that we have Driver class that creates and provides webdriver object.
1 webdriver = 1 browser
To access webdriver object, we need to call get() method from Driver class. This method is static because webdriver object is also static and can be called without instantiating Driver class.
When we have static webdriver object we ensure that everyone is sending commands to same browser.
Static methods/variables belong to class not to specific object.
Driver class is more advanced version of BrowserFactory class
When the required WebElement objects needs to match all of the given criteria use @FindBys annotation
When required WebElement objects needs to match at least one of the given criteria use @FindAll annotation
 public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss");
        String date = df.format(new Date());
        System.out.println(date);
    }
With TestNG framework we can use Extent report to generate nice and user friendly HTML report of test results. Since report is very important component of every test automation framework, we have to make it look nice.
To setup Extent report:
Step 1. IF your project is based on Maven, add dependency in pom.xml file:

        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>4.0.9</version>
        </dependency>
Step 2. WE need to add couple things into TestBase class.
###########################################GIT
###git status - to check what's new in your repository.
On branch master
Your branch is up to date with 'origin/master'.
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)
        new file:   colors.txt
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
        modified:   colors.txt
###git add colors.txt - to stage file (prepare this file for commit)
###git commit -m "added blue color" - to commit changes
[master f810eff] added blue color
 1 file changed, 1 insertion(+)
 create mode 100644 colors.txt
###git push - to send changes to remote repository (github)
Enumerating objects: 4, done.
Counting objects: 100% (4/4), done.
Delta compression using up to 16 threads
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 286 bytes | 286.00 KiB/s, done.
Total 3 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/CybertekSchool/Summer2019GitPractice.git
   9a0efc0..f810eff  master -> master
git pull
remote: Enumerating objects: 5, done.
remote: Counting objects: 100% (5/5), done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 3 (delta 1), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), done.
From https://github.com/CybertekSchool/Summer2019GitPractice
   cfbc2da..656144d  master     -> origin/master
Updating cfbc2da..656144d
error: Your local changes to the following files would be overwritten by merge:
        colors.txt
Please commit your changes or stash them before you merge.
Aborting
Because I modified a file that was changed by someone else before me, I cannot pull updates. This situation calls merge conflict.
    There are 3 solutions:
    1. Stash changes. It's when you save your changes on a side. Then, you can apply your changes again after pull.
    2. Discard changes. It's pretty much loss of your updates. If some file/files conflicts, you can discard your updates and pull code successfully.
    3. Resolve merge conflict right away.
        Step 1. add your file to the staging area (git add .)
        Step 2. Commit changes
        Step 3. pull updates from remote repository (git pull)
        Step 4. Open conflicting files and either manually fix them or use intellij conflict resolver, etc.
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git add colors.txt
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git commit -m "added green color"
[master f4900e6] added green color
 1 file changed, 1 insertion(+)
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git pull
Auto-merging colors.txt
CONFLICT (content): Merge conflict in colors.txt
Automatic merge failed; fix conflicts and then commit the result.
blue
red
<<<<<<< HEAD
green
=======
black
>>>>>>> 656144d36eb69365db5000eb67231b4c8c22f35a

======= - means where is a conflict in file
<<<<<<< HEAD under head - it's your changes
under ======= it's a changes that someone else did it before you. Last commit changes.
How to fix this conflict manually?
    You can manually edit file and decide what code has to stay, and then simply commit your changes again.
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git add colors.txt
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git commit -m "fixed merge conflict in colors.txt file"
[master ff239fa] fixed merge conflcit in colors.txt file
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git push
Enumerating objects: 10, done.
Counting objects: 100% (10/10), done.
Delta compression using up to 16 threads
Compressing objects: 100% (4/4), done.
Writing objects: 100% (6/6), 586 bytes | 586.00 KiB/s, done.
Total 6 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 1 local object.
To https://github.com/CybertekSchool/Summer2019GitPractice.git
   656144d..ff239fa  master -> master
conflict resolved!
you and someone else working in the same project in the same time, she/she pushed before you then you pushed your codes, conflict happens, since the version changed before you push.
If different files were modified, git will resolve conflict automatically. If same file was modified, you will have to resolve conflict manually.
if you are in vim editor, it's a terminal text edit, press I to insert text and type :q/:q! end hit enter to quit
Then just click control/command + c
git checkout filename - to discard all changes. File will be changed accordingly to last commit.
Pretty much we are loosing changes and file will have same content as at the last commit.
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git checkout colors.txt
Studios-iMac:Summer2019GitPractice CybertekStudios1$ git pull
Updating fed5fd5..e60b637
Fast-forward
 colors.txt | 1 +
 1 file changed, 1 insertion(+)
#######start typing file name or folder name and click TAB for auto-complete
Another way to discard ALL CHANGES: git reset --hard
#### git stash save "added cyber yellow color and gold"
stashing it's like moving your changes from cart to wish list. In this case you can resolve conflict and not lose your changes.
git stash list - to see your wish list, or collections of stashes
Developers stash changes when they didn't complete development but they need to pull updates. So to avoid braking a code, developers stash changes and open them when they pulled and can continue developing unfinished code.
<<<<<<< Updated upstream
French rose
=======
Cyber yellow
gold
>>>>>>> Stashed changes
top part is a latest changes
bottom part is a stashed code
git stash pop - to apply last stash to your code back
After git stash pop, resolve conflicts. Then commit your code again.
git stash list - to see list of stashes
#####after conflict warning messages:
1. git stash save "message"
2. git pull
3. git stash pop
4. git add .
5. git commit -m "...."
6. git push

     */
}
