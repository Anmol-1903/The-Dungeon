package thedungeon;
import java.util.*;
public class TheDungeon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        String[] enemies = {"Skeleton", "Zombie", "Phantom", "Brute", "Knight", "Ravager"};         //  ALL ENEMIES
        int maxEnemyHealth = 0;                                                                     //  all stats are randomized except initial player health
        int enemyAttackDamage = 0;
        int bowDamage = 30;
        int bowUses = 5;
        int spearDamage = 75;
        int spearUses = 2;
        int health = 100;
        int attackDamage = 50;
        int numHealthPotions = 5;
        int healthPotionHealAmt = 25;
        int healthPotionDropChance = 25;
        int bowDropChance = 30;
        int spearDropChance = 20;
        int score = 0;
        int damageBlocked;
        int enemyHealth=0;
        int points=0;
        boolean running = true;
        System.out.println("Looks like we have another player...");                                 //  INTRO
        System.out.println("Maybe you could last a bit longer than the other players...");
        System.out.println("Just remember it's not easy out there...");
        System.out.println("Do you really wish to enter the DUNGEON ? (y/n)");
        String temp = in.nextLine();
        if(temp.equals("y") || temp.equals("Y")){
            System.out.println("I knew you are brave enough. Let's see what you can do...");
        }
        else if(temp.equals("n") || temp.equals("N")){
            System.out.println("Too late, I've already made up my mind. Good luck...");
            
        }
        else{
            System.out.println("Looks like you don't know English...");
            System.out.println("Whatever, I'm sure you will like it in there. Bye...");
        }
        GAME:                                                                                       //  GAME BEGINS
        while(running){
            System.out.println("------------------------------------------------------------------------------------------------");
            String enemy = enemies[rand.nextInt(enemies.length)];
            if(enemy.equals("Skeleton")){
                maxEnemyHealth = 80;
                enemyAttackDamage = 25;
            }
            else if(enemy.equals("Zombie")){
                maxEnemyHealth = 60;
                enemyAttackDamage = 30;
            }
            else if(enemy.equals("Phantom")){
                maxEnemyHealth = 75;
                enemyAttackDamage = 10;
            }
            else if(enemy.equals("Brute")){
                maxEnemyHealth = 100;
                enemyAttackDamage = 50;
            }
            else if(enemy.equals("Knight")){
                maxEnemyHealth = 50;
                enemyAttackDamage = 50;
            }
            else if(enemy.equals("Ravager")){
                maxEnemyHealth = 150;
                enemyAttackDamage = 75;
            }
            System.out.println("\t# "+ enemy +" has appeared #\n");                                 //  ENEMY CHOSEN
            while(enemyHealth<5){
                enemyHealth=rand.nextInt(maxEnemyHealth);
            }
            while(enemyHealth>0){
                System.out.println("\tYour HP: "+ health);
                System.out.println("\t"+ enemy +"'s HP: "+ enemyHealth);
                System.out.println("\n\tWhat would you like to do ?");
                System.out.println("\t[1]. Attack");
                System.out.println("\t[2]. Block");
                System.out.println("\t[3]. Health Potion");
                System.out.println("\t[4]. Bow");
                System.out.println("\t[5]. spear");
                System.out.println("\t[6]. Flee");
                System.out.println("\t[7]. Info");
                String input = in.nextLine();
                
                if(input.equals("1")){                                                              //  ATTACK (0-50HP)
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    if(enemy.equals("Phantom")){
                        if(rand.nextInt(4)==0){
                            numHealthPotions--;
                            System.out.println("The Phantom striked you twice !!");
                        }
                    }
                    else if(enemy.equals("Brute")){
                        if(rand.nextInt(3)==0){
                            numHealthPotions--;
                            System.out.println("The Brute missed !!");
                            damageTaken = 0;
                        }
                        
                    }
                    else if(enemy.equals("Knight")){
                        if(rand.nextInt(2)==0){
                            numHealthPotions--;
                            System.out.println("You missed !!");
                            damageDealt = 0;
                        }
                        else{
                            damageDealt+=20;
                        }
                    }
                    enemyHealth -= damageDealt;
                    health -= damageTaken;
                    System.out.println("\t> You strike the "+ enemy +" for "+ damageDealt +" damage");
                    System.out.println("\t> You recieved "+ damageTaken +" damage in retaliation ");
                    if(health<1){
                        System.out.println("\t> You Died !!! ");
                        break;
                    }
                    points+=5;
                }
                else if(input.equals("2")){                                                         //  BLOCK (0-100%)
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    damageBlocked = rand.nextInt(damageTaken);
                    if(enemy.equals("Ravager")){
                        damageBlocked = 0;
                        System.out.println("\t> You tried to block a Ravager's attack ");
                    }
                    damageTaken -= damageBlocked;
                    if(enemy.equals("Skeleton")){
                        damageTaken/=2;
                    }
                    health -= damageTaken;
                    if(damageBlocked<1){
                        System.out.println("\t> You failed miserabely");
                        System.out.println("\t> You recieved "+ damageTaken +" in retaliation");
                    }
                    else{
                        System.out.println("\t> You blocked "+ damageBlocked +" damage");
                        System.out.println("\t> You recieved only "+ damageTaken +" in retaliation");
                    }
                    if(health<1){
                        System.out.println("\t> You Died !!! ");
                        break;
                    }
                    points+=3;
                }
                else if(input.equals("3")){                                                         //  HEAL (25HP)
                    if(numHealthPotions>0){
                        health += healthPotionHealAmt;
                        numHealthPotions--;
                        if(enemy.equals("Zombie")){
                            health-=10;
                            System.out.println("\t> You drank a health potion healing you for "+ healthPotionHealAmt +"\n\t> You have "+ numHealthPotions +" health potions left.\n");
                            System.out.println("\tThe "+ enemy +" attacked you for 10 damage while you were healing");
                        }
                        System.out.println("\t> You are now at "+ health +" HP\n");
                    }
                    else{
                        System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one.\n");                        
                    }
                    points+=2;
                }
                else if(input.equals("4")){                                                         //  BOW (0-30HP)
                    if(bowUses<1){
                        System.out.println("\t> You need a new Bow");
                    }
                    else{
                        if(enemy.equals("Phantom")){
                            int damageDealt = rand.nextInt(bowDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);
                            enemyHealth -= damageDealt;
                            health -= damageTaken;
                            System.out.println("\t> You shot the "+ enemy +" for "+ bowDamage*2 +" damage");
                            System.out.println("\t> You recieved "+ damageTaken +" damage in retaliation ");
                        }
                        else if(enemy.equals("Knight")){
                            int damageDealt = rand.nextInt(bowDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);
                            if(rand.nextInt(5) != 0){
                                damageDealt = 0;
                            }
                            enemyHealth -= damageDealt;
                            health -= damageTaken;
                            System.out.println("\t> You shot the "+ enemy +" for "+ bowDamage*2 +" damage");
                            System.out.println("\t> You recieved "+ damageTaken +" damage in retaliation ");
                        }
                        else{
                            int damageDealt = rand.nextInt(bowDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);
                            enemyHealth -= damageDealt;
                            health -= damageTaken;
                            System.out.println("\t> You shot the "+ enemy +" for "+ bowDamage*2 +" damage");
                            System.out.println("\t> You recieved "+ damageTaken +" damage in retaliation ");
                        }
                        bowUses--;
                        System.out.println("\t> You have "+ bowUses +" Bow uses left");
                    }
                    points+=4;
                }
                else if(input.equals("5")){
                    if(spearUses<1){
                        System.out.println("\t> You need a new Spear");
                    }
                    else{
                        if(enemy.equals("Ravager")){
                            int damageDealt = rand.nextInt(spearDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);
                            damageDealt*=2;
                            enemyHealth -= damageDealt;
                            health -= damageTaken;
                            System.out.println("\t> You speared the "+ enemy +" for "+ spearDamage*2 +" damage");
                            System.out.println("\t> You recieved "+ damageTaken +" damage in retaliation ");
                        }
                        else{
                            int damageDealt = rand.nextInt(spearDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);
                            enemyHealth -= damageDealt;
                            health -= damageTaken;
                            System.out.println("\t> You speared the "+ enemy +" for "+ spearDamage*2 +" damage");
                            System.out.println("\t> You recieved "+ damageTaken +" damage in retaliation ");
                        }
                        spearUses--;
                    }
                    points+=7;
                }
                else if(input.equals("6")){                                                         //  FLEE (always good unless facing a skeleton)
                    points-=20;
                    if(enemy.equals("Skeleton")){                                                   //  **costs points
                        health -= 15;
                        System.out.println("\tYou tried to run away from the "+ enemy);
                        System.out.println("\tThe "+ enemy +" shot you on your back for 25 damage");
                        if(health<1){
                            points-=10;
                            break;
                        }
                        else{
                            System.out.println("\tYou survived the shot, You are now at "+ health +" HP");
                            System.out.println("\tYou ran away from the "+ enemy);
                            continue GAME;
                        }
                    }
                    else{
                        System.out.println("\tYou ran away from the "+ enemy);
                        continue GAME;
                    }
                }
                else if(input.equals("7")){                                                         //  INFO
                    if(enemy.equals("Skeleton")){
                        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t+++ Can use its bow to shoot you for more damage if you try to flee +++");
                        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t----------------------------------------------------------------------------------------------------");
                        System.out.println("\t--- The arrows shot doesn't pierce through your shield when blocking, though dealing less damage ---");
                        System.out.println("\t----------------------------------------------------------------------------------------------------");
                        System.out.println("\t*****************");
                        System.out.println("\t*** Drops Bow ***");
                        System.out.println("\t*****************");
                    }
                    else if(enemy.equals("Zombie")){
                        System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t+++ Uses melee attacks, therefore damages player even if the player is healing +++");
                        System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t--------------------------------------");
                        System.out.println("\t--- It is weak and has no defences ---");
                        System.out.println("\t--------------------------------------");
                        System.out.println("\t**********************");
                        System.out.println("\t*** Healing Potion ***");
                        System.out.println("\t**********************");
                    }
                    else if(enemy.equals("Phantom")){
                        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t+++ A flying mob that dives onto the player and has a chance to deal damage two times you +++");
                        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t------------------------------");
                        System.out.println("\t--- Weak to bow and arrows ---");
                        System.out.println("\t------------------------------");
                    }
                    else if(enemy.equals("Brute")){
                        System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t+++ Uses iron sword, therefore dealing more damage +++");
                        System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t-----------------------------------------------------------------------");
                        System.out.println("\t--- Slow at attacking, therefore has a chance of missing the player ---");
                        System.out.println("\t-----------------------------------------------------------------------");
                    }
                    else if(enemy.equals("Knight")){
                        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t+++ Rides it's horse making it hard to shoot, and has a chance to dodge your attack +++");
                        System.out.println("\t+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t----------------------------------------------------");
                        System.out.println("\t--- Weak to melee attack if successfully striked ---");
                        System.out.println("\t----------------------------------------------------");
                        System.out.println("\t*******************");
                        System.out.println("\t*** Drops Spear ***");
                        System.out.println("\t*******************");
                    }
                    else if(enemy.equals("Ravager")){
                        System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t+++ A four legged mob that rams the player, damages even if the player is blocking +++");
                        System.out.println("\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("\t---------------------");
                        System.out.println("\t--- Weak to spear ---");
                        System.out.println("\t---------------------");
                    }
                }
                else{
                    System.out.println("Invalid Command !!!");
                }
            }
            if(health<1){                                                                           //  PLAYER DEATH
                points-=50;
                System.out.println("\nYou were defeated by the "+ enemy);
                System.out.println("You defeated "+ score +" enemies before fainting !!");
                System.out.println("You scored "+ points +" points !!");
                break;
            }
            else if(enemyHealth<1){                                                                 //  ENEMY DEATH
                score++;
                points+=10;
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println(" # The "+ enemy +" was defeated #");
                System.out.println(" # You have "+ health +" HP left #");
                if(enemy.equals("Zombie") && rand.nextInt(100)>healthPotionDropChance){
                    numHealthPotions++;
                    System.out.println(" # The "+ enemy +" dropped a health potion #");
                    System.out.println(" # You now have "+ numHealthPotions +" health potion(s) #");
                }
                else if(enemy.equals("Knight") && rand.nextInt(100)>spearDropChance){
                    spearUses++;
                    System.out.println(" # The "+ enemy +" dropped a spear #");
                    System.out.println(" # You now have "+ spearUses +" spear use(s) #");
                }
                else if(enemy.equals("Skeleton") && rand.nextInt(100)>bowDropChance){
                    bowUses+=5;
                    System.out.println(" # The "+ enemy +" dropped a bow #");
                    System.out.println(" # You now have "+ bowUses +" bow use(s) #");
                }
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("\nWhat would you like to do ?");
                System.out.println("[1]. Continue Fighting");
                System.out.println("[2]. Exit Dungeon");
                System.out.println("\t(If you die, you lose points) !!");
                String input = in.nextLine();
                while(!input.equals("1") && !input.equals("2")){
                    System.out.println("\tNot an option. Try again !!");
                    input = in.nextLine();
                }
                if(input.equals("1")){
                    System.out.println("\tYou continue on your adventure !!");
                }
                else if(input.equals("2")){
                    System.out.println("\tYou exitted the dungeon defeating "+ score +" enemies !!");
                    System.out.println("\tYou scored "+ points +" points !!");
                    break;
                }
            }
        }
        System.out.println("\t\t######################");
        System.out.println("\t\t# Thanks for playing #");
        System.out.println("\t\t######################");
    }
}