import { cookies } from "next/headers";

async function getEnrollments() {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    //Pega o id do aluno
    const meResponse = await fetch("http://localhost:3000/students/enrollments", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    const student = await meResponse.json();

    const enrollmentsResponse = await fetch(`http://localhost:3000/students/${student.id}/enrollments`, {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return enrollmentsResponse.json();
}

export default async function EnrollmentsPage() {
    const enrollments = await getEnrollments();

    return (
        <div>
            <h1>Aulas Matriculadas</h1>
        </div>
    );
}