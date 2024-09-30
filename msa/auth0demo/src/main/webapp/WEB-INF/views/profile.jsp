<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8; /* Soft background color */
            margin: 0;
            padding: 0;
        }

        .profile-container {
            max-width: 600px;
            margin: 50px auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .profile-container h2 {
            color: #0073e6;
            font-size: 32px;
            margin-bottom: 20px;
        }

        .profile-item {
            font-size: 18px;
            margin: 10px 0;
            color: #333;
        }

        .profile-item strong {
            color: #0073e6;
            font-weight: bold;
        }

        .profile-item img {
            max-width: 150px;
            height: auto;
            border-radius: 50%;
            margin-top: 20px;
        }

        .profile-picture {
            margin-bottom: 20px;
        }

        .edit-btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #0073e6;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .edit-btn:hover {
            background-color: #005bb5;
            transform: translateY(-3px);
        }

        .profile-container p {
            margin: 0;
        }

        .logout-btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #e60000;
            transform: translateY(-3px);
        }
    </style>
</head>
<body>

    <div class="profile-container">
        <h2>My Account</h2>
        <div class="profile-item profile-picture">
            <img src="${profile['picture']}" alt="Profile Picture" />
        </div>
        <p class="profile-item"><strong>Email:</strong> ${profile['email']}</p>
        <p class="profile-item"><strong>Name:</strong> ${profile['nickname']}</p>
        <p class="profile-item"><strong>Role:</strong> ${profile['role']}</p>

        <button class="edit-btn" onclick="window.location.href='/edit-profile'">Edit Profile</button>
        <button class="logout-btn" onclick="window.location.href='/logout'">Logout</button>
    </div>

</body>
</html>
