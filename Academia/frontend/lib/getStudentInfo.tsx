import { cookies } from "next/headers";

export async function getStudentInfo() {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    if (!token) {
        return null;
    }

    const response = await fetch("http://localhost:3000/students/me", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return response.json();
}