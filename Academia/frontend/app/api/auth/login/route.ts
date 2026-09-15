import { cookies } from "next/headers";

export async function POST(request: Request) {
    const { email, password } = await request.json();

    const response = await fetch("http://localhost:3000/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json", },
        body: JSON.stringify({ email, password }),
    })

    if (!response.ok) {
        return Response.json({ error: "Invalid credentials" }, { status: 401 })
    }

    const token = await response.json();

    const cookieStore = await cookies();
    cookieStore.set("token", token, {
        httpOnly: true,
        secure: false,
        maxAge: 3600,
        path: "/"
    })

    return Response.json({ message: "Login successful" }, { status: 200 })
}