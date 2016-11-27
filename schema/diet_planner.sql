-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2016 at 10:40 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diet_planner`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getApplicableFood` (IN `userIdInput` INT)  NO SQL
BEGIN
	SELECT dairy.id, food.foodName, food.foodType FROM dairy
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = dairy.id
    WHERE (diet.lactose & dairy.lactose) = 0
    
    UNION ALL
    
    SELECT fruit.id, food.foodName, food.foodType FROM fruit
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = fruit.id
    WHERE ((diet.nuts & fruit.nuts) | (diet.citrus & fruit.citrus)) = 0
    
    UNION ALL
    
    SELECT grains.id, food.foodName, food.foodType FROM grains
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = grains.id
    WHERE ((diet.gluten & grains.gluten) | (diet.whiteBread & grains.whiteBread) | (diet.wheat & grains.wheat) | (diet.oats & grains.oats) | (diet.rice & grains.rice) | (diet.corn & grains.corn)) = 0
    
    UNION ALL
    
    SELECT meat.id, food.foodName, food.foodType FROM meat
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = meat.id
    WHERE ((diet.egg & meat.egg) | (diet.seafood & meat.seafood) | (diet.redMeat & meat.redMeat) | (diet.shellfish & meat.shellfish) | (diet.shrimp & meat.shrimp)) = 0
    
    UNION ALL
    
    SELECT vegetables.id, food.foodName, food.foodType FROM vegetables
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = vegetables.id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getApplicableMuscleFood` (IN `userIdInput` INT)  NO SQL
BEGIN
	SELECT dairy.id, food.foodName, food.foodType FROM dairy
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = dairy.id
    JOIN nutritionalvalue ON dairy.id = nutritionalvalue.foodId
    WHERE (diet.lactose & dairy.lactose) = 0 AND nutritionalvalue.folicAcidDV > 0.65
    
    UNION ALL
    
    SELECT fruit.id, food.foodName, food.foodType FROM fruit
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = fruit.id
    JOIN nutritionalvalue ON fruit.id = nutritionalvalue.foodId
    WHERE ((diet.nuts & fruit.nuts) | (diet.citrus & fruit.citrus)) = 0 AND nutritionalvalue.folicAcidDV > 0.65
    
    UNION ALL
    
    SELECT grains.id, food.foodName, food.foodType FROM grains
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = grains.id
    JOIN nutritionalvalue ON grains.id = nutritionalvalue.foodId
    WHERE ((diet.gluten & grains.gluten) | (diet.whiteBread & grains.whiteBread)) = 0 AND (nutritionalvalue.folicAcidDV > 0.65 OR complexCarb = 1)
    
    UNION ALL
    
    SELECT meat.id, food.foodName, food.foodType FROM meat
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = meat.id
    JOIN nutritionalvalue ON meat.id = nutritionalvalue.foodId
    WHERE ((diet.egg & meat.egg) | (diet.seafood & meat.seafood) | (diet.redMeat & meat.redMeat) | (diet.shellfish & meat.shellfish) | (diet.shrimp & meat.shrimp)) = 0 AND (nutritionalvalue.proteinCount > 10 OR nutritionalvalue.folicAcidDV > 0.65)
    
    UNION ALL
    
    SELECT vegetables.id, food.foodName, food.foodType FROM vegetables
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = vegetables.id
    JOIN nutritionalvalue ON vegetables.id = nutritionalvalue.foodId
	WHERE nutritionalvalue.folicAcidDV > 0.65 OR complexCarb = 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getApplicableThinFood` (IN `userIdInput` INT)  NO SQL
BEGIN
	SELECT dairy.id, food.foodName, food.foodType FROM dairy
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = dairy.id
    JOIN nutritionalvalue ON dairy.id = nutritionalvalue.foodId
    WHERE (diet.lactose & dairy.lactose) = 0 AND dairy.lactose = 0
    
    UNION ALL
    
    SELECT fruit.id, food.foodName, food.foodType FROM fruit
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = fruit.id
    JOIN nutritionalvalue ON fruit.id = nutritionalvalue.foodId
    WHERE ((diet.nuts & fruit.nuts) | (diet.citrus & fruit.citrus)) = 0
    
    UNION ALL
    
    SELECT grains.id, food.foodName, food.foodType FROM grains
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = grains.id
    JOIN nutritionalvalue ON grains.id = nutritionalvalue.foodId
    WHERE ((diet.gluten & grains.gluten) | (diet.whiteBread & grains.whiteBread)) = 0
    
    UNION ALL
    
    SELECT meat.id, food.foodName, food.foodType FROM meat
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = meat.id
    JOIN nutritionalvalue ON meat.id = nutritionalvalue.foodId
    WHERE ((diet.egg & meat.egg) | (diet.seafood & meat.seafood) | (diet.redMeat & meat.redMeat) | (diet.shellfish & meat.shellfish) | (diet.shrimp & meat.shrimp)) = 0 AND meat.redMeat = 0
    
    UNION ALL
    
    SELECT vegetables.id, food.foodName, food.foodType FROM vegetables
    JOIN diet ON diet.userId = userIdInput
    JOIN food ON food.id = vegetables.id
    JOIN nutritionalvalue ON vegetables.id = nutritionalvalue.foodId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getFriendlyMealSchedule` (IN `userIdInput` INT)  NO SQL
SELECT person.id, mealschedule.foodId, food.foodName, food.foodType, mealschedule.day, mealschedule.dayTime, nutritionalvalue.calorieCount, SUM(nutritionalvalue.calorieCount) AS totalCalorie, (nutritionalvalue.transfatCount + nutritionalvalue.saturatedFatCount + nutritionalvalue.unsaturatedFatCount) AS totalFat, nutritionalvalue.carbCount, nutritionalvalue.proteinCount, nutritionalvalue.vitaminCDV, nutritionalvalue.calciumDV FROM mealschedule
JOIN person ON person.id = mealschedule.userId
JOIN food ON food.id = mealschedule.foodId
JOIN nutritionalvalue ON nutritionalvalue.foodId = mealschedule.foodId
WHERE person.id = userIdInput
GROUP BY mealschedule.day ORDER BY food.id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertDairy` (IN `foodNameInput` VARCHAR(20), IN `superfoodInput` INT, IN `lactoseInput` INT, IN `calorie` INT, IN `sugar` INT, IN `transFat` INT, IN `saturatedFat` INT, IN `unsaturatedFat` INT, IN `carb` INT, IN `protein` INT, IN `cholesterol` INT, IN `vitaminB` DOUBLE, IN `vitaminC` DOUBLE, IN `vitaminD` DOUBLE, IN `folicAcid` DOUBLE, IN `calcium` DOUBLE)  BEGIN
	SET @x = 0;
	INSERT INTO food (foodName, foodType, superfood) VALUES (foodNameInput, "dairy", superfoodInput);
    SELECT @x := food.id FROM food WHERE foodName = foodNameInput;
    UPDATE dairy SET lactose = lactoseInput WHERE dairy.id = @x;
    UPDATE nutritionalvalue SET
    	calorieCount = calorie,
        sugarCount = sugar,
        transfatCount = transFat,
        saturatedFatCount = saturatedFat,
        unsaturatedFatCount = unsaturatedFat,
        carbCount = carb,
        proteinCount = protein,
        cholesterolCount = cholesterol,
        vitaminBDV = vitaminB,
        vitaminCDV = vitaminC,
        vitaminDDV = vitaminD,
        folicAcidDV = folicAcid,
        calciumDV = calcium
    WHERE foodId = @x;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertFruit` (IN `foodNameInput` VARCHAR(20), IN `superfoodInput` INT, IN `nutsInput` INT, IN `citrusInput` INT, IN `legumeInput` INT, IN `calorie` INT, IN `sugar` INT, IN `transFat` INT, IN `saturatedFat` INT, IN `unsaturatedFat` INT, IN `carb` INT, IN `protein` INT, IN `cholesterol` INT, IN `vitaminB` DOUBLE, IN `vitaminC` DOUBLE, IN `vitaminD` DOUBLE, IN `folicAcid` DOUBLE, IN `calcium` DOUBLE)  BEGIN
    SET @x = 0;
    INSERT INTO food (foodName, foodType, superfood) VALUES (foodNameInput, "fruit", superfoodInput);
    SELECT @x := food.id FROM food WHERE foodName = foodNameInput;
    UPDATE fruit SET nuts = nutsInput, citrus = citrusInput, legume = legumeInput WHERE fruit.id = @x;
    UPDATE nutritionalvalue SET
        calorieCount = calorie,
        sugarCount = sugar,
        transfatCount = transFat,
        saturatedFatCount = saturatedFat,
        unsaturatedFatCount = unsaturatedFat,
        carbCount = carb,
        proteinCount = protein,
        cholesterolCount = cholesterol,
        vitaminBDV = vitaminB,
        vitaminCDV = vitaminC,
        vitaminDDV = vitaminD,
        folicAcidDV = folicAcid,
        calciumDV = calcium
   WHERE foodId = @x;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertGrains` (IN `foodNameInput` VARCHAR(20), IN `superfoodInput` INT, IN `glutenInput` INT, IN `whiteBreadInput` INT, IN `wheatInput` INT, IN `oatsInput` INT, IN `riceInput` INT, IN `cornInput` INT, IN `complexCarbInput` INT, IN `calorie` INT, IN `sugar` INT, IN `transFat` INT, IN `saturatedFat` INT, IN `unsaturatedFat` INT, IN `carb` INT, IN `protein` INT, IN `cholesterol` INT, IN `vitaminB` DOUBLE, IN `vitaminC` DOUBLE, IN `vitaminD` DOUBLE, IN `folicAcid` DOUBLE, IN `calcium` DOUBLE)  BEGIN
    SET @x = 0;
    INSERT INTO food (foodName, foodType, superfood) VALUES (foodNameInput, "grains", superfoodInput);
    SELECT @x := food.id FROM food WHERE foodName = foodNameInput;
    UPDATE grains SET gluten = glutenInput, whiteBread = whiteBreadInput, wheat = wheatInput, oats = oatsInput, rice = riceInput, corn = cornInput,
    complexCarb = complexCarbInput WHERE grains.id = @x;
    UPDATE nutritionalvalue SET
        calorieCount = calorie,
        sugarCount = sugar,
        transfatCount = transFat,
        saturatedFatCount = saturatedFat,
        unsaturatedFatCount = unsaturatedFat,
        carbCount = carb,
        proteinCount = protein,
        cholesterolCount = cholesterol,
        vitaminBDV = vitaminB,
        vitaminCDV = vitaminC,
        vitaminDDV = vitaminD,
        folicAcidDV = folicAcid,
        calciumDV = calcium
   WHERE foodId = @x;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertMeat` (IN `foodNameInput` VARCHAR(20), IN `superfoodInput` INT, IN `eggInput` INT, IN `seafoodInput` INT, IN `meatTypeInput` VARCHAR(20), IN `redMeatInput` INT, IN `shellfishInput` INT, IN `shrimpInput` INT, IN `calorie` INT, IN `sugar` INT, IN `transFat` INT, IN `saturatedFat` INT, IN `unsaturatedFat` INT, IN `carb` INT, IN `protein` INT, IN `cholesterol` INT, IN `vitaminB` DOUBLE, IN `vitaminC` DOUBLE, IN `vitaminD` DOUBLE, IN `folicAcid` DOUBLE, IN `calcium` DOUBLE)  BEGIN
    SET @x = 0;
    INSERT INTO food (foodName, foodType, superfood) VALUES (foodNameInput, "meat", superfoodInput);
    SELECT @x := food.id FROM food WHERE foodName = foodNameInput;
    UPDATE meat SET egg = eggInput, seafood = seafoodInput, meatType = meatTypeInput, redMeat = redMeatInput, shellfish = shellfishInput, shrimp = shrimpInput WHERE meat.id = @x;
    UPDATE nutritionalvalue SET
        calorieCount = calorie,
        sugarCount = sugar,
        transfatCount = transFat,
        saturatedFatCount = saturatedFat,
        unsaturatedFatCount = unsaturatedFat,
        carbCount = carb,
        proteinCount = protein,
        cholesterolCount = cholesterol,
        vitaminBDV = vitaminB,
        vitaminCDV = vitaminC,
        vitaminDDV = vitaminD,
        folicAcidDV = folicAcid,
        calciumDV = calcium
   WHERE foodId = @x;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertVegetables` (IN `foodNameInput` VARCHAR(20), IN `superfoodInput` INT, IN `typeOfVegetableInput` VARCHAR(20), IN `legumeInput` INT, IN `complexCarbInput` INT, IN `calorie` INT, IN `sugar` INT, IN `transFat` INT, IN `saturatedFat` INT, IN `unsaturatedFat` INT, IN `carb` INT, IN `protein` INT, IN `cholesterol` INT, IN `vitaminB` DOUBLE, IN `vitaminC` DOUBLE, IN `vitaminD` DOUBLE, IN `folicAcid` DOUBLE, IN `calcium` DOUBLE)  BEGIN
    SET @x = 0;
    INSERT INTO food (foodName, foodType, superfood) VALUES (foodNameInput, "vegetables", superfoodInput);
    SELECT @x := food.id FROM food WHERE foodName = foodNameInput;
    UPDATE vegetables SET typeOfVegetable = typeOfVegetableInput, legume = legumeInput, complexCarb = complexCarbInput WHERE vegetables.id = @x;
    UPDATE nutritionalvalue SET
        calorieCount = calorie,
        sugarCount = sugar,
        transfatCount = transFat,
        saturatedFatCount = saturatedFat,
        unsaturatedFatCount = unsaturatedFat,
        carbCount = carb,
        proteinCount = protein,
        cholesterolCount = cholesterol,
        vitaminBDV = vitaminB,
        vitaminCDV = vitaminC,
        vitaminDDV = vitaminD,
        folicAcidDV = folicAcid,
        calciumDV = calcium
   WHERE foodId = @x;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dairy`
--

CREATE TABLE `dairy` (
  `id` int(11) NOT NULL,
  `foodType` varchar(20) DEFAULT 'dairy',
  `lactose` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dairy`
--

INSERT INTO `dairy` (`id`, `foodType`, `lactose`) VALUES
(1, 'dairy', 1),
(2, 'dairy', 0),
(3, 'dairy', 1),
(4, 'dairy', 1),
(5, 'dairy', 1),
(6, 'dairy', 1),
(7, 'dairy', 1),
(8, 'dairy', 1);

-- --------------------------------------------------------

--
-- Table structure for table `diet`
--

CREATE TABLE `diet` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `dailyCalorieCount` int(11) DEFAULT '2147483647',
  `dailySugarCount` int(11) DEFAULT '2147483647',
  `dailyCarbCount` int(11) DEFAULT '2147483647',
  `dailyCholesterolCount` int(11) DEFAULT '2147483647',
  `egg` tinyint(1) DEFAULT '0',
  `seafood` tinyint(1) DEFAULT '0',
  `redMeat` tinyint(1) DEFAULT '0',
  `shellfish` tinyint(1) DEFAULT '0',
  `shrimp` tinyint(1) DEFAULT '0',
  `legume` tinyint(1) DEFAULT '0',
  `lactose` tinyint(1) DEFAULT '0',
  `gluten` tinyint(1) DEFAULT '0',
  `whiteBread` tinyint(1) DEFAULT '0',
  `wheat` tinyint(1) DEFAULT '0',
  `oats` tinyint(1) DEFAULT '0',
  `rice` tinyint(1) DEFAULT '0',
  `corn` tinyint(1) DEFAULT '0',
  `dailySaturatedFat` int(11) DEFAULT '2147483647',
  `dailyUnsaturatedFat` int(11) DEFAULT '2147483647',
  `dailyTransFat` int(11) DEFAULT '2147483647',
  `nuts` tinyint(1) DEFAULT '0',
  `citrus` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diet`
--

INSERT INTO `diet` (`id`, `userId`, `dailyCalorieCount`, `dailySugarCount`, `dailyCarbCount`, `dailyCholesterolCount`, `egg`, `seafood`, `redMeat`, `shellfish`, `shrimp`, `legume`, `lactose`, `gluten`, `whiteBread`, `wheat`, `oats`, `rice`, `corn`, `dailySaturatedFat`, `dailyUnsaturatedFat`, `dailyTransFat`, `nuts`, `citrus`) VALUES
(3, 5, 2147483647, 2147483647, 2147483647, 2147483647, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2147483647, 2147483647, 2147483647, 0, 0),
(4, 6, 2147483647, 2147483647, 2147483647, 2147483647, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2147483647, 2147483647, 2147483647, 0, 0),
(6, 8, 2147483647, 2147483647, 2147483647, 2147483647, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2147483647, 2147483647, 2147483647, 0, 0),
(7, 9, 2147483647, 2147483647, 2147483647, 2147483647, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2147483647, 2147483647, 2147483647, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `foodName` varchar(20) DEFAULT NULL,
  `foodType` varchar(20) NOT NULL,
  `superfood` tinyint(1) DEFAULT '0',
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`foodName`, `foodType`, `superfood`, `id`) VALUES
('milk', 'dairy', 0, 1),
('yogurt', 'dairy', 0, 2),
('ice cream', 'dairy', 0, 3),
('cheddar cheese', 'dairy', 0, 4),
('parmesan cheese', 'dairy', 0, 5),
('mozzarella cheese', 'dairy', 0, 6),
('blue cheese', 'dairy', 0, 7),
('gouda cheese', 'dairy', 0, 8),
('apple', 'fruit', 0, 9),
('orange', 'fruit', 0, 10),
('lemon', 'fruit', 0, 11),
('avocado', 'fruit', 1, 12),
('oatmeal', 'grains', 0, 13),
('chuck beef', 'meat', 0, 14),
('chicken breast', 'meat', 0, 15),
('broccoli', 'vegetables', 1, 16),
('lion', 'meat', 0, 17),
('sweet potato', 'vegetables', 0, 18),
('beef jerkey', 'meat', 0, 19);

--
-- Triggers `food`
--
DELIMITER $$
CREATE TRIGGER `addFoodToType` AFTER INSERT ON `food` FOR EACH ROW BEGIN
	INSERT INTO nutritionalvalue (foodId) VALUES (NEW.id);
    
    CASE (NEW.foodType)
        WHEN "dairy" THEN
            INSERT INTO dairy (id)
            VALUES (NEW.id);
        WHEN "fruit" THEN
            INSERT INTO fruit (id)
            VALUES (NEW.id);
        WHEN "grains" THEN
            INSERT INTO grains (id)
            VALUES (NEW.id);
        WHEN "meat" THEN
            INSERT INTO meat (id)
            VALUES (NEW.id);
        ELSE
            INSERT INTO vegetables (id)
            VALUES (NEW.id);
	END CASE;
    
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `fruit`
--

CREATE TABLE `fruit` (
  `id` int(11) NOT NULL,
  `foodType` varchar(20) DEFAULT 'fruit',
  `nuts` tinyint(1) DEFAULT '0',
  `citrus` tinyint(1) DEFAULT '0',
  `legume` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fruit`
--

INSERT INTO `fruit` (`id`, `foodType`, `nuts`, `citrus`, `legume`) VALUES
(9, 'fruit', 0, 1, 0),
(10, 'fruit', 0, 1, 0),
(11, 'fruit', 0, 1, 0),
(12, 'fruit', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `grains`
--

CREATE TABLE `grains` (
  `id` int(11) NOT NULL,
  `foodType` varchar(20) DEFAULT 'grains',
  `gluten` tinyint(1) DEFAULT '0',
  `whiteBread` tinyint(1) DEFAULT '0',
  `complexCarb` tinyint(1) DEFAULT '0',
  `wheat` tinyint(1) DEFAULT '0',
  `oats` tinyint(1) DEFAULT '0',
  `rice` tinyint(1) DEFAULT '0',
  `corn` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grains`
--

INSERT INTO `grains` (`id`, `foodType`, `gluten`, `whiteBread`, `complexCarb`, `wheat`, `oats`, `rice`, `corn`) VALUES
(13, 'grains', 1, 0, 1, 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `mealschedule`
--

CREATE TABLE `mealschedule` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `day` int(11) DEFAULT '0',
  `dayTime` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mealschedule`
--

INSERT INTO `mealschedule` (`id`, `userId`, `foodId`, `quantity`, `day`, `dayTime`) VALUES
(14, 6, 1, 1, 0, 0),
(15, 6, 2, 1, 1, 1),
(16, 6, 3, 1, 2, 0),
(17, 6, 1, 1, 0, 0),
(18, 6, 2, 1, 1, 1),
(19, 6, 3, 1, 2, 2),
(20, 6, 10, 1, 3, 0),
(21, 6, 11, 1, 4, 1),
(22, 6, 16, 1, 5, 2),
(23, 6, 1, 1, 0, 0),
(24, 6, 8, 1, 1, 1),
(25, 6, 10, 1, 2, 2),
(26, 6, 12, 1, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `meat`
--

CREATE TABLE `meat` (
  `id` int(11) NOT NULL,
  `foodType` varchar(20) DEFAULT 'meat',
  `meatType` varchar(20) DEFAULT NULL,
  `egg` tinyint(1) DEFAULT '0',
  `seafood` tinyint(1) DEFAULT '0',
  `redMeat` tinyint(1) DEFAULT '0',
  `shellfish` tinyint(1) DEFAULT '0',
  `shrimp` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meat`
--

INSERT INTO `meat` (`id`, `foodType`, `meatType`, `egg`, `seafood`, `redMeat`, `shellfish`, `shrimp`) VALUES
(14, 'meat', 'beef', 0, 0, 1, 0, 0),
(15, 'meat', 'chicken', 0, 0, 0, 0, 0),
(17, 'meat', NULL, 0, 0, 0, 0, 0),
(19, 'meat', NULL, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `nutritionalvalue`
--

CREATE TABLE `nutritionalvalue` (
  `id` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `calorieCount` int(11) DEFAULT '0',
  `sugarCount` int(11) DEFAULT '0',
  `transfatCount` int(11) DEFAULT '0',
  `saturatedFatCount` int(11) DEFAULT '0',
  `unsaturatedFatCount` int(11) DEFAULT '0',
  `carbCount` int(11) DEFAULT '0',
  `proteinCount` int(11) DEFAULT '0',
  `cholesterolCount` int(11) DEFAULT '0',
  `vitaminBDV` double DEFAULT '0',
  `vitaminCDV` double DEFAULT '0',
  `vitaminDDV` double DEFAULT '0',
  `folicAcidDV` double DEFAULT '0',
  `calciumDV` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nutritionalvalue`
--

INSERT INTO `nutritionalvalue` (`id`, `foodId`, `calorieCount`, `sugarCount`, `transfatCount`, `saturatedFatCount`, `unsaturatedFatCount`, `carbCount`, `proteinCount`, `cholesterolCount`, `vitaminBDV`, `vitaminCDV`, `vitaminDDV`, `folicAcidDV`, `calciumDV`) VALUES
(1, 1, 103, 13, 0, 2, 1, 12, 8, 12, 0.18, 0, 0, 0, 0.3),
(2, 2, 100, 6, 0, 0, 0, 6, 17, 9, 0.21, 0, 0, 0, 0.18),
(3, 3, 137, 14, 0, 5, 2, 16, 2, 29, 0.05, 0, 0.01, 0.02, 0.08),
(4, 4, 113, 0, 0, 6, 3, 0, 7, 29, 0.03, 0, 0.01, 0, 0.2),
(5, 5, 22, 0, 0, 1, 1, 0, 2, 4, 0.01, 0, 0, 0, 0.05),
(6, 6, 78, 0, 0, 3, 2, 1, 8, 15, 0.05, 0, 0.01, 0, 0.2),
(7, 7, 477, 1, 0, 25, 12, 3, 30, 101, 0.26, 0, 0.07, 0, 0.71),
(8, 8, 101, 1, 0, 5, 2, 1, 7, 32, 0, 0, 0.01, 0, 0.19),
(9, 9, 95, 19, 0, 0, 0, 25, 1, 0, 0, 0.14, 0, 0.17, 0.01),
(10, 10, 45, 9, 0, 0, 0, 11, 1, 0, 0, 0.85, 0, 0.09, 0.03),
(11, 11, 17, 2, 0, 0, 0, 5, 1, 0, 0, 0.51, 0, 0.06, 0.01),
(12, 12, 234, 1, 0, 3, 17, 12, 3, 0, 0, 0.24, 0, 0.4, 0.01),
(13, 13, 158, 1, 0, 1, 2, 27, 6, 0, 0, 0, 0, 0.16, 0.18),
(14, 14, 852, 0, 4, 27, 34, 0, 77, 268, 1.55, 0, 0.03, 0, 0.04),
(15, 15, 231, 0, 0, 1, 3, 0, 43, 119, 0.08, 0, 0.01, 0, 0.02),
(16, 16, 50, 3, 0, 0, 0, 10, 4, 0, 0, 2.2, 0, 0.15, 0.07),
(17, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(18, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(19, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `pword` varchar(20) NOT NULL,
  `age` int(11) NOT NULL DEFAULT '18',
  `gender` varchar(6) NOT NULL DEFAULT 'male',
  `height` int(11) NOT NULL DEFAULT '60',
  `weight` int(11) NOT NULL DEFAULT '100',
  `BMI` double DEFAULT NULL,
  `athletic` tinyint(1) NOT NULL DEFAULT '0',
  `admin` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `username`, `pword`, `age`, `gender`, `height`, `weight`, `BMI`, `athletic`, `admin`) VALUES
(4, 'ARROW', 'OLIVER', 18, 'male', 60, 100, NULL, 0, 0),
(5, 'SDFD', 'DDD', 18, 'male', 60, 100, NULL, 0, 0),
(6, 'jojo', 'dio', 18, 'male', 60, 100, NULL, 0, 0),
(8, 'speedwagon', 'abs', 22, 'female', 0, 0, NULL, 0, 1),
(9, 'tarkus', 'blue', 22, 'female', 0, 0, NULL, 0, 1);

--
-- Triggers `person`
--
DELIMITER $$
CREATE TRIGGER `addDiet` AFTER INSERT ON `person` FOR EACH ROW INSERT INTO diet (userId)
VALUES (NEW.id)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `vegetables`
--

CREATE TABLE `vegetables` (
  `id` int(11) NOT NULL,
  `foodType` varchar(20) DEFAULT 'vegetables',
  `typeOfVegetable` varchar(20) DEFAULT NULL,
  `legume` tinyint(1) DEFAULT '0',
  `complexCarb` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vegetables`
--

INSERT INTO `vegetables` (`id`, `foodType`, `typeOfVegetable`, `legume`, `complexCarb`) VALUES
(16, 'vegetables', 'floret', 0, 0),
(18, 'vegetables', NULL, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dairy`
--
ALTER TABLE `dairy`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dairy_ibfk_1` (`id`,`foodType`);

--
-- Indexes for table `diet`
--
ALTER TABLE `diet`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userId` (`userId`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`,`foodType`);

--
-- Indexes for table `fruit`
--
ALTER TABLE `fruit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`foodType`);

--
-- Indexes for table `grains`
--
ALTER TABLE `grains`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`foodType`);

--
-- Indexes for table `mealschedule`
--
ALTER TABLE `mealschedule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meat`
--
ALTER TABLE `meat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`foodType`);

--
-- Indexes for table `nutritionalvalue`
--
ALTER TABLE `nutritionalvalue`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `foodId` (`foodId`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `vegetables`
--
ALTER TABLE `vegetables`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`foodType`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diet`
--
ALTER TABLE `diet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `mealschedule`
--
ALTER TABLE `mealschedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `nutritionalvalue`
--
ALTER TABLE `nutritionalvalue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `dairy`
--
ALTER TABLE `dairy`
  ADD CONSTRAINT `dairy_ibfk_1` FOREIGN KEY (`id`,`foodType`) REFERENCES `food` (`id`, `foodType`) ON DELETE CASCADE;

--
-- Constraints for table `diet`
--
ALTER TABLE `diet`
  ADD CONSTRAINT `diet_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `person` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `fruit`
--
ALTER TABLE `fruit`
  ADD CONSTRAINT `fruit_ibfk_1` FOREIGN KEY (`id`,`foodType`) REFERENCES `food` (`id`, `foodType`) ON DELETE CASCADE;

--
-- Constraints for table `grains`
--
ALTER TABLE `grains`
  ADD CONSTRAINT `grains_ibfk_1` FOREIGN KEY (`id`,`foodType`) REFERENCES `food` (`id`, `foodType`) ON DELETE CASCADE;

--
-- Constraints for table `meat`
--
ALTER TABLE `meat`
  ADD CONSTRAINT `meat_ibfk_1` FOREIGN KEY (`id`,`foodType`) REFERENCES `food` (`id`, `foodType`) ON DELETE CASCADE;

--
-- Constraints for table `nutritionalvalue`
--
ALTER TABLE `nutritionalvalue`
  ADD CONSTRAINT `nutritionDelete` FOREIGN KEY (`foodId`) REFERENCES `food` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `vegetables`
--
ALTER TABLE `vegetables`
  ADD CONSTRAINT `vegetables_ibfk_1` FOREIGN KEY (`id`,`foodType`) REFERENCES `food` (`id`, `foodType`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
