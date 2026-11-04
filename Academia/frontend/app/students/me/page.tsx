import { getStudentInfo } from "@/lib/getStudentInfo";

export default async function StudentInfoPage() {
    const student = await getStudentInfo();

    return (
        <div>
            <h1 className="text-2xl font-bold">Student Information</h1>
            <p className="text-gray-600">Name: {student?.name}</p>
            <p className="text-gray-600">Age: {student.age}</p>
            <p className="text-gray-600">Email: {student.email}</p>
            <p className="text-gray-600">PlanType: {student.planType}</p>
            <p className="text-gray-600">PlanStart: {student.planStart}</p>
            <p className="text-gray-600">PlanEnd: {student.planEnd}</p>
            <p className="text-gray-600">PlanStatus: {student.planActive ? "Ativo" : "Inactive"}</p>
        </div>
    );
}