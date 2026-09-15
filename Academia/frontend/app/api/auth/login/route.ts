import { cookies } from "next/headers";

function decodeJwtPayload(token: string) {const payloadBase64 = token.split('.')[1];
    const payloadJson = Buffer.from(payloadBase64, 'base64').toString('utf-8');
    return JSON.parse(payloadJson);
}

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

    const token = await response.text();

    const cookieStore = await cookies();
    cookieStore.set("token", token, {
        httpOnly: true,
        secure: false,
        maxAge: 3600,
        path: "/"
    })

    const payload = decodeJwtPayload(token);

    return Response.json({ role: payload.role })
}